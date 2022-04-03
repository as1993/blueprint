package com.rivi.blueprint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rivi.blueprint.dto.UserStoryRequestDto;
import com.rivi.blueprint.entity.UserStory;
import com.rivi.blueprint.service.impl.UserStoryService;

@RestController
@RequestMapping("/api/v1/userstories")
public class UserStoryController {

	private final UserStoryService userStoryService;

	public UserStoryController(UserStoryService userStoryService) {
		this.userStoryService = userStoryService;
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserStory get(@PathVariable Long id) {
		return userStoryService.getUserStory(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserStory> getAll() {
		return userStoryService.getAllUserStories();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserStory post(@RequestBody UserStoryRequestDto userStory) {
		return userStoryService.createUserStory(userStory);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserStory put(@PathVariable Long id, @RequestBody UserStoryRequestDto userStory) {
		return userStoryService.updateUserStory(id, userStory);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userStoryService.deleteUserStory(id);
	}
}
