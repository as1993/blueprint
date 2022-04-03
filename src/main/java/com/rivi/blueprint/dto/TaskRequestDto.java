package com.rivi.blueprint.dto;

import lombok.Data;

@Data
public class TaskRequestDto {

	private String title;
	private String description;
	private Long userId;
	private Long userStoryId;
}
