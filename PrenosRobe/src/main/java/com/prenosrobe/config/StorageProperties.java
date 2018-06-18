package com.prenosrobe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties
{
	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";

	/**
	 * Get the location.
	 *
	 * @return location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * Set the location.
	 *
	 * @param location new location
	 */
	public void setLocation(final String location)
	{
		this.location = location;
	}
}
