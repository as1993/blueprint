package com.rivi.blueprint.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserStoryRequestDto {

	private String title;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate completionDate;
	private Integer stateId;
	private Long userId;
}
