package com.rivi.blueprint.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rivi.blueprint.dto.UserStoryRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.entity.UserStory;
import com.rivi.blueprint.enums.State;
import com.rivi.blueprint.exception.BadRequestException;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.exception.UserStoryNotFoundException;
import com.rivi.blueprint.repository.UserRepository;
import com.rivi.blueprint.repository.UserStoryRepository;
import com.rivi.blueprint.service.IUserStoryService;

@Service
public class UserStoryService implements IUserStoryService {

	private final UserStoryRepository userStoryRepository;
	private final UserRepository userRepository;

	public UserStoryService(UserStoryRepository userStoryRepository, UserRepository userRepository) {
		this.userStoryRepository = userStoryRepository;
		this.userRepository = userRepository;
	}

	@Override
	public UserStory getUserStory(Long userStoryId) {
		return userStoryRepository.findById(userStoryId).orElseThrow(
				() -> new UserStoryNotFoundException("User Story with id " + userStoryId + " does not exists!"));
	}

	@Override
	public List<UserStory> getAllUserStories() {
		return (List<UserStory>) userStoryRepository.findAll();
	}

	@Override
	public UserStory createUserStory(UserStoryRequestDto storyDto) {
		validateUserStoryRequest(storyDto);
		UserStory story = mapDtoToEntity(storyDto);
		return userStoryRepository.save(story);
	}

	@Override
	public UserStory updateUserStory(Long userStoryId, UserStoryRequestDto newStory) {
		UserStory story = userStoryRepository.findById(userStoryId).orElseThrow(
				() -> new UserStoryNotFoundException("User Story with id " + userStoryId + " does not exists!"));

		if (newStory.getTitle() != null) {
			story.setTitle(newStory.getTitle());
		}
		if (newStory.getDescription() != null) {
			story.setDescription(newStory.getDescription());
		}
		if (State.valueOf(newStory.getStateId()) != null) {
			story.setState(State.valueOf(newStory.getStateId()));
		}
		if (newStory.getCompletionDate() != null) {
			story.setCompletionDate(newStory.getCompletionDate());
		}

		return userStoryRepository.save(story);
	}

	@Override
	public void deleteUserStory(Long userStoryId) {
		Optional<UserStory> story = userStoryRepository.findById(userStoryId);
		if (story.isPresent()) {
			userStoryRepository.delete(story.get());
		}
	}

	private void validateUserStoryRequest(UserStoryRequestDto storyDto) {
		if (!StringUtils.hasLength(storyDto.getTitle())) {
			throw new BadRequestException("Story Title cannot be empty!");
		}
		if (storyDto.getCompletionDate() != null && LocalDate.now().isAfter(storyDto.getCompletionDate())) {
			throw new BadRequestException("Completion date is in the past!");
		}
	}

	private UserStory mapDtoToEntity(UserStoryRequestDto dto) {
		UserStory userStory = new UserStory();

		if (dto.getTitle() != null) {
			userStory.setTitle(dto.getTitle());
		}

		if (dto.getDescription() != null) {
			userStory.setDescription(dto.getDescription());
		}

		if (dto.getCompletionDate() != null) {
			userStory.setCompletionDate(dto.getCompletionDate());
		}

		if (dto.getStateId() != null) {
			userStory.setState(State.valueOf(dto.getStateId()));
		}

		if (dto.getUserId() != null) {
			User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);
			userStory.setAssignee(user);
		}

		return userStory;
	}
}
