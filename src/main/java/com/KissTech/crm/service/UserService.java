package com.KissTech.crm.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.KissTech.crm.model.User;
import com.KissTech.crm.repository.UserRepository;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    
    private final PasswordEncoder encoder;



	public UserService(UserRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	public User create(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

     
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}



	public List<String> getAllUniqueUsers() {
		List<User> users = userRepository.findAll();
		Set<String> getAllUniqueUser = users.stream()
				.map(User::getRole)
				.collect(Collectors.toSet());
		return new ArrayList<>(getAllUniqueUser);
	}
    
    

}
