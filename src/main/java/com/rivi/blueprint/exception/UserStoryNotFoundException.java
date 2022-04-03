package com.rivi.blueprint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserStoryNotFoundException extends RuntimeException {

	public UserStoryNotFoundException() {
		super("User Story does not exists!");
	}

	public UserStoryNotFoundException(String message) {
		super(message);
	}
}
