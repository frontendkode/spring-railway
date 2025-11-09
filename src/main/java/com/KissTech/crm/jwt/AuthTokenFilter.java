package com.KissTech.crm.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.KissTech.crm.DTO.MessageResponseDTO;
import com.KissTech.crm.config.ActiveSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	private AuthTokenFilter jwtFilter;

	private final JwtUtils jwtUtils;
	private final ActiveSessionService activeSessionService;

	@Autowired
	public AuthTokenFilter(JwtUtils jwtUtils, ActiveSessionService activeSessionService) {
		this.jwtUtils = jwtUtils;
		this.activeSessionService = activeSessionService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = jwtUtils.getTokenFromRequest(request);

		if (token != null && jwtUtils.validateToken(token)) {
			String username = jwtUtils.getUsernameFromToken(token);
			if (activeSessionService.isTokenValidForUser(username, token)) {
				var authentication = jwtUtils.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				// Token is invalidated due to login on another device
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				// Create a MessageResponse instance with the appropriate message
				MessageResponseDTO messageResponse = new MessageResponseDTO("Session invalidated: logged in on another device.",401);

				// Set the response content type to JSON
				response.setContentType("application/json");

				// Write the MessageResponse as JSON to the response body
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().write(objectMapper.writeValueAsString(messageResponse));

				return;  // Terminate the filter chain, as the session is invalid
			}
		}

		// Continue with the filter chain if token is valid or no token
		filterChain.doFilter(request, response);
	}


//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.exceptionHandling()
//				.authenticationEntryPoint(unauthorizedHandler)  // 👈 this is important
//				.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authorizeRequests()
//				.antMatchers("/auth/**").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	}


}
