package com.prenosrobe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.prenosrobe.config.StorageProperties;
import com.prenosrobe.service.FileSystemStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService)
	{
		return args -> storageService.init();
	}
}
