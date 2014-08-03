package com.themallmall.rest;

/**
 * @author Edward P. Legaspi
 **/
public class RestApiException extends Exception {

	private static final long serialVersionUID = -8654566805824077453L;

	public RestApiException() {
		super();
	}

	public RestApiException(String message) {
		super(message);
	}
}
