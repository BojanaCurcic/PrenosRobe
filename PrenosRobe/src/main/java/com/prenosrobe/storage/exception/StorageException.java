package com.prenosrobe.storage.exception;

public class StorageException extends RuntimeException
{
	private static final long serialVersionUID = -2099235223906477508L;

	public StorageException(String message)
	{
		super(message);
	}

	public StorageException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
