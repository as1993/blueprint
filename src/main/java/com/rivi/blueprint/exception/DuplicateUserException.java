package com.rivi.blueprint.exception;

public class DuplicateUserException extends RuntimeException {

	public DuplicateUserException() {
		super("User already present!");
	}

	public DuplicateUserException(String message) {
		super(message);
	}
}
