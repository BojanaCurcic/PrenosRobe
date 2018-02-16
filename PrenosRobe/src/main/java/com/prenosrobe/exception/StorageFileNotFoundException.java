package com.prenosrobe.exception;

public class StorageFileNotFoundException extends ApplicationException
{
	private static final long serialVersionUID = -7527185539634725504L;

	/**
	 * Instantiate a new storage file not found exception.
	 *
	 * @param message message
	 */
	public StorageFileNotFoundException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiate a new storage file not found exception.
	 *
	 * @param message message
	 * @param cause cause
	 */
	public StorageFileNotFoundException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
