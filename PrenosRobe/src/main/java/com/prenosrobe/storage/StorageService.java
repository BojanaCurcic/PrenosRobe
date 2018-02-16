package com.prenosrobe.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService
{
	public void init();

	public void upload(MultipartFile file);

	public Stream<Path> loadAll();

	public Path load(String filename);

	public Resource loadAsResource(String filename);
}
