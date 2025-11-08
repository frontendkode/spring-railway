package com.KissTech.crm.serviceimpl;




import java.security.SecureRandom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KissTech.crm.model.User;
import com.KissTech.crm.repository.UserRepository;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	@Transactional
	public UserDetails findByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public String forgotPassword(String username) {

			User user = userRepository.findByUsername(username);

			if (user.getUsername() == "") {
				return "Invalid Username.";
			}

			user.setToken(getOtp());
			user = userRepository.save(user);

			return user.getToken();
		}

	public String getOtp() {
		int LENGHT = 5;

		SecureRandom randomizer = new SecureRandom();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= LENGHT; i++) {
			builder.append(randomizer.nextInt(10));
		}

		String otp = builder.toString();
		return otp;
	}
	}







	




