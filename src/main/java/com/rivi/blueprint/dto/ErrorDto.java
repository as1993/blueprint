package com.rivi.blueprint.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorDto {
	private final HttpStatus httpStatus;
	private final String message;
}
