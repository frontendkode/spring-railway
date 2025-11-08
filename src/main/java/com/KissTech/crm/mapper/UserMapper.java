package com.KissTech.crm.mapper;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.KissTech.crm.DTO.UserDTO;
import com.KissTech.crm.createDTO.CreateUserDTO;
import com.KissTech.crm.model.User;



@Component
public class UserMapper {
	
	@Lazy
	private final PasswordEncoder encoder;
	

	public UserMapper(PasswordEncoder encoder) {
		super();
		this.encoder = encoder;
	}

	public User createDtoToModel(CreateUserDTO createUserDTO) {
		User user = new User();
		user.setUsername(createUserDTO.getUsername());
		user.setPassword(encoder.encode(createUserDTO.getPassword()));
		LocalDateTime currentDateTime = LocalDateTime.now();
		user.setTokenCreationDate(currentDateTime);
        user.setRole(createUserDTO.getRole());
		return user;
	}

	public UserDTO modelToDto(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setId(user.getId());
		dto.setRole(user.getRole());
		return dto;
	}
 

}
