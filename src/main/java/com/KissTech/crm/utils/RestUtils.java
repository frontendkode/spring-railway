package com.KissTech.crm.utils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

/**
 * The Class RestUtils.
 */
@Slf4j

public class RestUtils {

	/**
	 * Success response.
	 *
	 * @param <T>        the generic type
	 * @param data       the data
	 * @param statusCode the status code
	 * @return the response entity
	 */
	public static <T> ResponseEntity<RestResponse<T>> successResponse(final T data, final String message,
			final HttpStatus statusCode) {
		return new ResponseEntity<>(new RestResponse<T>(data, message), statusCode);
	}

	/**
	 * Success response.
	 *
	 * @param <T>  the generic type
	 * @param data the data
	 * @return the response entity
	 */
	public static <T> ResponseEntity<RestResponse<T>> successResponse(final T data, final String message) {
		return new ResponseEntity<>(new RestResponse<T>(data, message), HttpStatus.OK);
	}

	/**
	 * Success response.
	 *
	 * @param <T>  the generic type
	 * @param data the data
	 * @return the response entity
	 */
	public static <T> ResponseEntity<RestResponse<T>> successResponse(final T data) {
		return successResponse(data, null, HttpStatus.OK);
	}

	/**
	 * Success response.
	 *
	 * @param <T> the generic type
	 * @return the response entity
	 */
	public static <T> ResponseEntity<RestResponse<T>> successResponseMessage(final String message) {
		return successResponse(null, message, HttpStatus.OK);
	}

	/**
	 * Error response.
	 *
	 * @param <T>          the generic type
	 * @param errorMessage the error message
	 * @param statusCode   the status code
	 * @return the response entity
	 */
	public static <T> ResponseEntity<RestResponse<?>> errorResponse(final String errorMessage,
			final HttpStatus statusCode) {
		return new ResponseEntity<>(new RestResponse<T>(errorMessage, null, Boolean.FALSE, statusCode.value()),
				statusCode);
	}

	public static <T> ResponseEntity<RestResponse<?>> errorResponse(final String errorMessage) {
		return new ResponseEntity<>(
				new RestResponse<T>(errorMessage, null, Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR.value()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Error response.
	 *
	 * @param <T>              the generic type
	 * @param errorMessage     the error message
	 * @param errorDescription the error description
	 * @param statusCode       the status code
	 * @return the response entity
	 */
//	public static <T> ResponseEntity<RestResponse<?>> errorResponse(final String errorMessage, final T errorDescription,
//			final HttpStatus statusCode) {
//		log.info(statusCode.name() + " - " + statusCode.value());
//		log.error(errorMessage);
//		if (Objects.nonNull(errorDescription))
//		return new ResponseEntity<>(
//				new RestResponse<T>(errorMessage, errorDescription, Boolean.FALSE, statusCode.value()), statusCode);
//	}

	public static Optional<String> getCurrentLoginUserName() {
		// TODO Auto-generated method stub
		return getCurrentLoginUserName();
	}
}
