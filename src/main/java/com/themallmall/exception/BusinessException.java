package com.themallmall.exception;

/**
 * @author Edward P. Legaspi
 **/
public class BusinessException extends Exception {

	private static final long serialVersionUID = -5461782108646286263L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

}
