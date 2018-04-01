package com.prenosrobe.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prenosrobe.dto.RestRespondeDto;

public class ResponseEntityUtil
{
	/**
	 * Instantiate a new ResponseEntityUtil.
	 */
	private ResponseEntityUtil()
	{
	}

	/**
	 * Create ResponseEntity with status no content.
	 *
	 * @param message message
	 * @return response entity
	 */
	public static ResponseEntity<RestRespondeDto> createResponseEntityNoContent(
			final String message)
	{
		List<String> errorList = new ArrayList<>();
		errorList.add(message);

		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.NO_CONTENT.value(), errorList),
				HttpStatus.NO_CONTENT);
	}

	/**
	 * Create ResponseEntity with status forbidden.
	 *
	 * @return response entity
	 */
	public static ResponseEntity<RestRespondeDto> createResponseEntityForbidden()
	{
		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.FORBIDDEN.value()),
				HttpStatus.FORBIDDEN);
	}

	/**
	 * Create ResponseEntity with status already reported.
	 *
	 * @param errorList error list
	 * @return response entity
	 */
	public static ResponseEntity<RestRespondeDto> createResponseEntityAlreadyReported(
			List<String> errorList)
	{
		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.ALREADY_REPORTED.value(), errorList),
				HttpStatus.ALREADY_REPORTED);
	}

	/**
	 * Create ResponseEntity with status bad request.
	 *
	 * @param errorList error list
	 * @return response entity
	 */
	public static ResponseEntity<RestRespondeDto> createResponseEntityBadRequest(
			List<String> errorList)
	{
		return new ResponseEntity<>(new RestRespondeDto(HttpStatus.BAD_REQUEST.value(), errorList),
				HttpStatus.BAD_REQUEST);
	}
}
