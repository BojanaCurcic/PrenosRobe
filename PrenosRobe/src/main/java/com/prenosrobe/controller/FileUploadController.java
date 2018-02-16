package com.prenosrobe.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.prenosrobe.exception.Messages;
import com.prenosrobe.exception.StorageFileNotFoundException;
import com.prenosrobe.service.UserService;
import com.prenosrobe.storage.StorageService;

@Controller
public class FileUploadController
{
	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	/**
	 * List all stored files.
	 *
	 * @param token token used for user identification
	 * @param filesPlaceholder placeHolder for stored files
	 * @return list of stored files
	 */
	@GetMapping("/files")
	public ResponseEntity<Model> listStoredFiles(@RequestHeader(value = "token") String token,
			Model filesPlaceholder)
	{
		if (userService.authentication(token))
		{
			filesPlaceholder.addAttribute("files", storageService.loadAll()
					.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
							"getFile", token, path.getFileName().toString()).build().toString())
					.collect(Collectors.toList()));

			return new ResponseEntity<>(filesPlaceholder, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Get the stored file.
	 *
	 * @param token token used for user identification
	 * @param filename filename
	 * @return file
	 */
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@RequestHeader(value = "token") String token,
			@PathVariable String filename)
	{
		if (userService.authentication(token))
		{
			Resource file = storageService.loadAsResource(filename);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Upload the file.
	 *
	 * @param token token used for user identification
	 * @param file file
	 */
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestHeader(value = "token") String token,
			@RequestParam("file") MultipartFile file)
	{
		if (userService.authentication(token))
		{
			try
			{
				storageService.upload(file);
			} catch (Exception e)
			{
				return new ResponseEntity<>(Messages.NOT_UPLOADED + file.getOriginalFilename(),
						HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(Messages.SUCCESSFULLY_UPLOADED + file.getOriginalFilename(),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Handle storage file not found.
	 *
	 * @param exc exception
	 */
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<String> handleStorageFileNotFound(StorageFileNotFoundException exc)
	{
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
