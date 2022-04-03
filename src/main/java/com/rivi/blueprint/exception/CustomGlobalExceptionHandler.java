package com.rivi.blueprint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rivi.blueprint.dto.ErrorDto;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserStoryNotFoundException.class)
	public ResponseEntity<ErrorDto> handleUserStoryNotFoundException(UserStoryNotFoundException ex) {
		ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
		ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());
	}
}
