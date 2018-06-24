package com.prenosrobe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prenosrobe.dto.RestRespondeDto;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.FileSystemStorageService;
import com.prenosrobe.service.UserService;
import com.prenosrobe.util.ResponseEntityUtil;

@Controller
@RequestMapping("/api")
public class FileSystemStorageController
{
	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Autowired
	private UserService userService;

	/**
	 * List all stored filenames.
	 *
	 * @param token token used for user identification
	 * @return list of stored filenames
	 */
	@GetMapping("/files")
	public ResponseEntity<RestRespondeDto> listStoredFilenames(
			@RequestHeader(value = "token") String token)
	{
		if (userService.authentication(token))
		{
			List<String> files = new ArrayList<>();
			fileSystemStorageService.loadAll().map(path -> files.add(path.getFileName().toString()))
					.collect(Collectors.toList());

			RestRespondeDto restRespondeDto = new RestRespondeDto(HttpStatus.OK.value());
			restRespondeDto.setData(files);

			return new ResponseEntity<>(restRespondeDto, HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
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
	public ResponseEntity<RestRespondeDto> getFile(@RequestHeader(value = "token") String token,
			@PathVariable String filename)
	{
		if (userService.authentication(token))
		{
			Resource file = fileSystemStorageService.loadAsResource(filename);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + file.getFilename() + "\"");

			// TODO: proveri da li radi sa App, posto Postman zahteva da se prosledi iskljucivo file
			return new ResponseEntity<>(new RestRespondeDto(HttpStatus.OK.value(), file), headers,
					HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}

	/**
	 * Store the file.
	 *
	 * @param token token used for user identification
	 * @param file file
	 */
	@PostMapping("/store")
	public ResponseEntity<RestRespondeDto> storeFile(@RequestHeader(value = "token") String token,
			@RequestParam("file") MultipartFile file)
	{
		if (userService.authentication(token))
		{
			fileSystemStorageService.store(file);

			return new ResponseEntity<>(
					new RestRespondeDto(HttpStatus.OK.value(),
							Messages.SUCCESSFULLY_STORED + file.getOriginalFilename()),
					HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}
}
