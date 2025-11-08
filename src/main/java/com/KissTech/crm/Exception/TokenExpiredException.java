package com.KissTech.crm.Exception;

public class TokenExpiredException extends RuntimeException {

	public TokenExpiredException(String message) {
		super(message);
	}
}
