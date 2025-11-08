package com.KissTech.crm.apiService;


import com.KissTech.crm.DTO.*;
import com.KissTech.crm.Exception.InvalidOTPException;
import com.KissTech.crm.Exception.TokenExpiredException;
import com.KissTech.crm.Exception.UserNotFoundException;
import com.KissTech.crm.jwt.JwtUtils;
import com.KissTech.crm.model.User;
import com.KissTech.crm.repository.SessionRepository;
import com.KissTech.crm.repository.UserRepository;
import com.KissTech.crm.response.JwtResponse;
import com.KissTech.crm.service.SessionService;
import com.KissTech.crm.serviceimpl.UserDetailsImpl;
import com.KissTech.crm.serviceimpl.UserDetailsServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;




@Service
public class AuthApiService {

	private final UserRepository userRepository;

	private final JwtUtils jwtUtils;

	private final AuthenticationManager authenticationManager;

	private final UserDetailsServiceImpl userService;

	private final TemplateEngine templateEngine;

	private final JavaMailSender mailSender;

	private final PasswordEncoder encode;
	private final SessionService sessionService;


	public AuthApiService(UserRepository userRepository, JwtUtils jwtUtils, AuthenticationManager authenticationManager,
						  UserDetailsServiceImpl userService, TemplateEngine templateEngine, JavaMailSender mailSender,
						  PasswordEncoder encode, SessionService sessionService) {
		super();
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.templateEngine = templateEngine;
		this.mailSender = mailSender;
		this.encode = encode;
		this.sessionService = sessionService;
	}





//	public JwtDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
//	    try {
//	        // Authenticate the user
//	        Authentication authentication = authenticationManager.authenticate(
//	                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
//
//	        // Get user details
//	        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        String jwt = jwtUtils.generateJwtToken(userDetails);
//
//	        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getRole(),userDetails.getDivision(),"Authentication successful");
//
//	        // Return JwtDTO object
//	        return new JwtDTO(jwtResponse);
//	    } catch (BadRequest e) {
//	        // Create JwtResponse object with failure message
//	        JwtResponse jwtResponse = new JwtResponse(null, null, null,null,null, "Invalid username or password");
//
//	        // Return JwtDTO object
//	        return new JwtDTO(jwtResponse);
//	    }
//	}


	@Autowired
	private SessionRepository sessionRepository;

//	@Transactional
//	public JwtDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
//		// Auth part
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(
//						loginRequestDTO.getUsername(),
//						loginRequestDTO.getPassword()
//				));
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		String jwt = jwtUtils.generateJwtToken(userDetails);
//
//		// Delete and Save Session
//		sessionRepository.deleteByUsername(userDetails.getUsername());
//		sessionRepository.flush(); // this is the key point!
//
//		Session session = new Session();
//		session.setUsername(userDetails.getUsername());
//		session.setSessionId(jwt);
//		session.setLoginTime(LocalDateTime.now());
//		session.setLastAccessTime(LocalDateTime.now());
//		sessionRepository.save(session);
//
//		// Create Response
//		JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(),
//				userDetails.getUsername(), userDetails.getRole(), userDetails.getDivision(),
//				"Authentication successful");
//
//		return new JwtDTO(jwtResponse);
//	}
//




	public JwtDTO authenticateUser(LoginRequestDTO loginRequestDTO) {

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getUsername(),
						loginRequestDTO.getPassword())
		);

		// Get authenticated user details
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Single-device login logic
		String existingSessionToken = sessionService.getActiveSession(userDetails.getId());
		System.out.println("mass"+existingSessionToken);

		if (existingSessionToken != null) {
			sessionService.invalidateSession(userDetails.getId()); // Invalidate previous session
		}

		// Generate JWT and save active session
		String jwt = jwtUtils.generateJwtToken(authentication);
		sessionService.saveActiveSession(userDetails.getId(), jwt);

		// Create JWT response with user details
		JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(),
				userDetails.getUsername(), userDetails.getRole(),
				"Authentication successful");

		return new JwtDTO(jwtResponse);


	}







	public RespoDTO authenticateUser(String username) {
		String response = userService.forgotPassword(username);
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException("Enter Valid Usernamne");
		}

		// Send the password reset email
		String from = "ganeshbalajikingktr@gmail.com";
		String to = "ganeshbalajivenkat@gmail.com";
		String Username = user.getUsername();

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject("Password Reset");

			Context context = new Context();
			context.setVariable("username", username);
			context.setVariable("otp", response);

			// Load your HTML template file for password reset as a String
			String template = templateEngine.process("ForgotTemplate.html", context);

			helper.setText(template, true);

			mailSender.send(message);

			return new RespoDTO("OTP sent to admin email, kindly check with admin.");
		} catch (MessagingException ex) {
			// Handle any exceptions occurred while sending email
			ex.printStackTrace(); // Or log the error
			throw new RuntimeException("Error sending email"); // Or handle the error accordingly
		}
	}




	public MessageResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO) {
		// Find the user by token
		User user = userRepository.findByToken(resetPasswordDTO.getToken());

		// Check if user exists
		if (user == null) {
			throw new InvalidOTPException("Invalid OTP or Username.");
		}

		// Get the token creation date
		LocalDateTime tokenCreationDate = user.getTokenCreationDate();

		// Check if token creation date is null
		if (tokenCreationDate == null) {
			throw new TokenExpiredException("Invalid");
		}

		// Check if the token has expired (adjust expiration time as needed)
		LocalDateTime currentDateTime = LocalDateTime.now();
		long minutesElapsed = ChronoUnit.MINUTES.between(tokenCreationDate, currentDateTime);
		long expirationTimeInMinutes =900; // Adjust this value based on your expiration time

		// If the token has expired, throw an exception
		if (minutesElapsed > expirationTimeInMinutes) {
			throw new TokenExpiredException("Token has expired.");
		}

		// Update user password and reset token-related fields
		user.setPassword(encode.encode(resetPasswordDTO.getPassword()));
		user.setToken(null);
		user.setTokenCreationDate(currentDateTime);

		// Save the updated user information
		userRepository.save(user);

		// Respond with success message
		return new MessageResponseDTO("Password reset successfully.",200);
	}





}