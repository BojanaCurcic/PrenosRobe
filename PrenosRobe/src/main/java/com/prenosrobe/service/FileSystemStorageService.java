package com.prenosrobe.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.prenosrobe.config.StorageProperties;
import com.prenosrobe.exception.ApplicationException;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.exception.StorageFileNotFoundException;

@Service
public class FileSystemStorageService
{
	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties)
	{
		this.rootLocation = Paths.get(properties.getLocation());
	}

	/**
	 * Store the file.
	 *
	 * @param file file
	 */
	public void store(MultipartFile file)
	{
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try
		{
			if (file.isEmpty())
			{
				throw new ApplicationException(Messages.STORE_EMPTY_FILE + filename);
			}
			if (filename.contains(".."))
			{
				throw new ApplicationException(Messages.STORE_FILE_WITH_RELATIVE_PATH + filename);
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e)
		{
			throw new ApplicationException(Messages.STORE_FILE + filename, e);
		} catch (Exception e)
		{
			throw new StorageFileNotFoundException(
					Messages.NOT_STORED + file.getOriginalFilename());
		}
	}

	/**
	 * Load all stored files from root location.
	 */
	public Stream<Path> loadAll()
	{
		try
		{
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e)
		{
			throw new ApplicationException(Messages.READ_STORED_FILES, e);
		}
	}

	/**
	 * Load the stored file path.
	 * 
	 * @param filename filename
	 * @return file path
	 */
	public Path load(String filename)
	{
		return rootLocation.resolve(filename);
	}

	/**
	 * Load the stored file.
	 * 
	 * @param filename filename
	 * @return stored file 
	 */
	public Resource loadAsResource(String filename)
	{
		try
		{
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable())
			{
				return resource;
			}
			else
			{
				throw new StorageFileNotFoundException(Messages.READ_FILE + filename);
			}
		} catch (MalformedURLException e)
		{
			throw new StorageFileNotFoundException(Messages.READ_FILE + filename, e);
		}
	}

	/**
	 * Init root location.
	 */
	public void init()
	{
		try
		{
			Files.createDirectories(rootLocation);
		} catch (IOException e)
		{
			throw new ApplicationException(Messages.INIT_STORAGE, e);
		}
	}
}
