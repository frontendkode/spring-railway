package com.KissTech.crm.createDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateUserDTO {

	

	private String username;
	

	private String password;
	


	private String role;
}
