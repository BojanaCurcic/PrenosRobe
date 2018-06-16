package com.prenosrobe.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prenosrobe.dto.RestRespondeDto;
import com.prenosrobe.util.ResponseEntityUtil;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler
{
	/**
	 * Handle application exception.
	 *
	 * @param exc exception
	 */
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<RestRespondeDto> handleApplicationException(ApplicationException exc)
	{
		List<String> errorList = new ArrayList<>();
		errorList.add(exc.getMessage());

		return ResponseEntityUtil.createResponseEntityBadRequest(errorList);
	}
}
