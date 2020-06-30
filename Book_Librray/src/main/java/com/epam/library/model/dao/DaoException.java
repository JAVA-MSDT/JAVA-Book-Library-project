package com.epam.library.model.dao;

public class DaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6353119994357207562L;

	public DaoException() {
		super();
	}

	public DaoException(final String message) {
		super(message);
	}

	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DaoException(final Throwable cause) {
		super(cause);
	}
}
