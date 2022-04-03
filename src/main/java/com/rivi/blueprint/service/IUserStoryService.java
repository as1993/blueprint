package com.rivi.blueprint.service;

import java.util.List;

import com.rivi.blueprint.dto.UserStoryRequestDto;
import com.rivi.blueprint.entity.UserStory;

public interface IUserStoryService {

	UserStory createUserStory(UserStoryRequestDto story);

	void deleteUserStory(Long userStoryId);

	List<UserStory> getAllUserStories();

	UserStory getUserStory(Long id);

	UserStory updateUserStory(Long userStoryId, UserStoryRequestDto newStory);

}