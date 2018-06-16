package com.prenosrobe.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.prenosrobe.dto.RestRespondeDto;
import com.prenosrobe.exception.Messages;
import com.prenosrobe.service.UserService;
import com.prenosrobe.storage.StorageService;
import com.prenosrobe.util.ResponseEntityUtil;

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
	public ResponseEntity<RestRespondeDto> listStoredFiles(
			@RequestHeader(value = "token") String token, Model filesPlaceholder)
	{
		if (userService.authentication(token))
		{
			filesPlaceholder.addAttribute("files", storageService.loadAll()
					.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
							"getFile", token, path.getFileName().toString()).build().toString())
					.collect(Collectors.toList()));

			return new ResponseEntity<>(
					new RestRespondeDto(HttpStatus.OK.value(), filesPlaceholder), HttpStatus.OK);
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
			Resource file = storageService.loadAsResource(filename);
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
	 * Upload the file.
	 *
	 * @param token token used for user identification
	 * @param file file
	 */
	@PostMapping("/upload")
	public ResponseEntity<RestRespondeDto> uploadFile(@RequestHeader(value = "token") String token,
			@RequestParam("file") MultipartFile file)
	{
		if (userService.authentication(token))
		{
			storageService.upload(file);

			return new ResponseEntity<>(
					new RestRespondeDto(HttpStatus.OK.value(),
							Messages.SUCCESSFULLY_UPLOADED + file.getOriginalFilename()),
					HttpStatus.OK);
		}
		return ResponseEntityUtil.createResponseEntityForbidden();
	}
}
