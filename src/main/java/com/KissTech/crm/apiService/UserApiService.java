package com.KissTech.crm.apiService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KissTech.crm.DTO.UserDTO;

import com.KissTech.crm.Exception.UsernameAlreadyExistsException;
import com.KissTech.crm.createDTO.CreateUserDTO;
import com.KissTech.crm.mapper.UserMapper;
import com.KissTech.crm.model.User;
import com.KissTech.crm.service.UserService;



@Service
public class UserApiService {

	
	private final UserService userService;
	
	private final UserMapper userMapper;

	public UserApiService(UserService userService, UserMapper userMapper) {
		super();
		this.userService = userService;
		this.userMapper = userMapper;
	}

	public UserDTO save(CreateUserDTO createUserDTO) {
		if (userService.existsByUsername(createUserDTO.getUsername())) {
	        // If username already exists, throw an exception or return an error message
	        throw new UsernameAlreadyExistsException("Username already exists");
	    }
		return userMapper.modelToDto(userService.create(userMapper.createDtoToModel(createUserDTO)));
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userService.findByUsername(username);
	}





	public List<String> getAllUniqueUsers() {
		// TODO Auto-generated method stub
		return userService.getAllUniqueUsers();
	}

	

	
	
	
}
