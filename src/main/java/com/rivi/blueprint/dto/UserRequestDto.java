package com.rivi.blueprint.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRequestDto {

	@NonNull
	private String name;
	@NonNull
	private String email;
	private Integer userTypeId;
}
