package com.epam.library.model.db;

public class ConnectionPoolException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5077530694780280383L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(final String message) {
		super(message);
	}

	public ConnectionPoolException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(final Throwable cause) {
		super(cause);
	}
}
