package com.prenosrobe.exception;

public class ApplicationException extends RuntimeException
{
	private static final long serialVersionUID = -2099235223906477508L;

	/**
	 * Instantiate a new storage exception.
	 *
	 * @param message message
	 */
	public ApplicationException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new storage exception.
	 *
	 * @param message message
	 * @param cause cause
	 */
	public ApplicationException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
