package com.prenosrobe.exception;

public class DataNotSavedException extends ApplicationException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiate a new data not saved exception.
	 *
	 * @param message message
	 */
	public DataNotSavedException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiate a new data not saved exception.
	 *
	 * @param message message
	 * @param cause cause
	 */
	public DataNotSavedException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
