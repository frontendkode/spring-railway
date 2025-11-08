package com.KissTech.crm.response;

import java.util.UUID;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private UUID id;
	private String username;
	private String role;

    private String message;
	
	

	


	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JwtResponse(String jwt, UUID id, String username ,String role, String message ) {
		super();
		this.token = jwt;
		this.type = type;
		this.id = id;
		this.username = username;
		this.role=role;

		this.message=message;
	}

	


}
