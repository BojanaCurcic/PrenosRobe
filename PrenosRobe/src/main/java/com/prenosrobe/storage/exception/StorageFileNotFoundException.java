package com.prenosrobe.storage.exception;

public class StorageFileNotFoundException extends StorageException
{
	private static final long serialVersionUID = -7527185539634725504L;

	public StorageFileNotFoundException(String message)
	{
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
