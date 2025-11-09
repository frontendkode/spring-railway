package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {

	private UUID id;
	
	private String username;
			
	private String division;
		
	private String role;
	
}
