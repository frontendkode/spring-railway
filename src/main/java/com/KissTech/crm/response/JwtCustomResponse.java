package com.KissTech.crm.response;

import lombok.Data;

@Data
public class JwtCustomResponse {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
    private String message;
    
    
	public JwtCustomResponse(String token, Long id, String username, String message) {
		super();
		this.token = token;
		this.type = type;
		this.id = id;
		this.username = username;
		this.message = message;
	}
    
    
}
