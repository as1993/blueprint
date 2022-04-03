package com.rivi.blueprint.dto;

import lombok.Data;

@Data
public class UserRequestDto {

	private String name;
	private String email;
	private Integer userTypeId;
}
