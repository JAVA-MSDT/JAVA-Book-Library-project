package com.epam.library.model.service;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835532108692470695L;

	public ServiceException() {
		super();
	}

	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ServiceException(final Throwable cause) {
		super(cause);
	}
}
