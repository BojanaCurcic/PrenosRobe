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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.prenosrobe.storage.StorageService;
import com.prenosrobe.storage.exception.StorageFileNotFoundException;

@Controller
public class FileUploadController
{
	@Autowired
	private StorageService storageService;

	@GetMapping("/files")
	public ResponseEntity<Model> listUploadedFiles(Model model)
	{
		model.addAttribute("files", storageService.loadAll()
				.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toString())
				.collect(Collectors.toList()));

		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename)
	{
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file)
	{
		// public String handleFileUpload(@RequestParam("file") MultipartFile file,
		// RedirectAttributes redirectAttributes) {
		// storageService.store(file);
		// redirectAttributes.addFlashAttribute("message",
		// "You successfully uploaded " + file.getOriginalFilename() + "!");
		try
		{
			storageService.store(file);
		} catch (Exception e)
		{
			return new ResponseEntity<>(
					"File \'" + file.getOriginalFilename() + "\' is not uploaded!",
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(
				"File \'" + file.getOriginalFilename() + "\' is successfully uploaded!",
				HttpStatus.OK);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc)
	{
		return ResponseEntity.notFound().build();
	}

}
