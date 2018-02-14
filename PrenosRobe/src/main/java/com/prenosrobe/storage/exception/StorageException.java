package com.prenosrobe.storage.exception;

public class StorageException extends RuntimeException
{
	private static final long serialVersionUID = -2099235223906477508L;

	/**
	 * Instantiate a new storage exception.
	 *
	 * @param message message
	 */
	public StorageException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new storage exception.
	 *
	 * @param message message
	 * @param cause cause
	 */
	public StorageException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
