package com.KissTech.crm.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class RestResponse<T> {

	private String responseMessage;
	private Boolean responseStatus = true;
	private Integer responseStatusCode = 200;
	private T responseObject;

	public RestResponse(String responseMessage, T responseObject, Boolean responseStatus, Integer responseStatusCode) {
		this.responseMessage = responseMessage;
		this.responseObject = responseObject;
		this.responseStatus = responseStatus;
		this.responseStatusCode = responseStatusCode;
	}

	public RestResponse(T data) {
		this.responseObject = data;
	}

	public RestResponse(T data, String responseMessage) {
		this.responseObject = data;
		this.responseMessage = responseMessage;
	}



	public RestResponse(String responseMessage) {
		this.responseMessage = responseMessage;
	}



}


