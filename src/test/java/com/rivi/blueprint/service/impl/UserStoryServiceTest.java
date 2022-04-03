package com.rivi.blueprint.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.rivi.blueprint.dto.UserStoryRequestDto;
import com.rivi.blueprint.entity.UserStory;
import com.rivi.blueprint.exception.BadRequestException;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.exception.UserStoryNotFoundException;
import com.rivi.blueprint.repository.UserRepository;
import com.rivi.blueprint.repository.UserStoryRepository;

@ExtendWith(MockitoExtension.class)
class UserStoryServiceTest {

	@Mock
	UserStoryRepository userStoryRepository;
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserStoryService userStoryService;

	@Test
	void getUserStory_success() {
		UserStory story = getSampleUserStory();
		Mockito.when(userStoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(story));
		assertNotNull(userStoryService.getUserStory(1L));
	}

	@Test
	void getUserStory_fail() {
		Mockito.when(userStoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(UserStoryNotFoundException.class, () -> userStoryService.getUserStory(1L));
	}

	@Test
	void getAllUserStories() {
		Mockito.when(userStoryRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(userStoryService.getAllUserStories());
	}

	@Test
	void createUserStory_success() {
		UserStory story = getSampleUserStory();
		UserStoryRequestDto dto = new UserStoryRequestDto();
		BeanUtils.copyProperties(story, dto);
		dto.setStateId(story.getState().getId());

		Mockito.when(userStoryRepository.save(Mockito.any(UserStory.class))).thenReturn(story);

		UserStory saved = userStoryService.createUserStory(dto);

		assertNotNull(saved);
		assertEquals(dto.getTitle(), saved.getTitle());
		assertEquals(dto.getStateId(), saved.getState().getId());
	}

	@Test
	void createUserStory_invalidRequest() {
		UserStory story = getSampleUserStory();
		UserStoryRequestDto dto = new UserStoryRequestDto();
		BeanUtils.copyProperties(story, dto);

		dto.setCompletionDate(LocalDate.now().minusDays(1));

		assertThrows(BadRequestException.class, () -> userStoryService.createUserStory(dto));

		dto.setCompletionDate(LocalDate.now().plusDays(1));
		dto.setTitle(null);

		assertThrows(BadRequestException.class, () -> userStoryService.createUserStory(dto));
	}

	@Test
	void createUserStory_userDoesNotExists() {
		UserStory story = getSampleUserStory();
		UserStoryRequestDto dto = new UserStoryRequestDto();
		BeanUtils.copyProperties(story, dto);
		dto.setUserId(1L);

		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> userStoryService.createUserStory(dto));
	}

	@Test
	void updateUserStory_success() {
		UserStory story = getSampleUserStory();
		long id = story.getId();
		UserStoryRequestDto dto = new UserStoryRequestDto();
		dto.setTitle("New Title");
		dto.setDescription("New Description");
		dto.setCompletionDate(LocalDate.now().plusDays(5));
		dto.setStateId(1);

		Mockito.when(userStoryRepository.findById(story.getId())).thenReturn(Optional.ofNullable(story));
		Mockito.when(userStoryRepository.save(Mockito.any(UserStory.class))).thenReturn(story);

		UserStory updated = userStoryService.updateUserStory(1L, dto);

		assertNotNull(updated);
		assertEquals(dto.getTitle(), updated.getTitle());
		assertEquals(id, updated.getId());
	}

	@Test
	void updateUserStory_storyDoesNotExists() {

		Mockito.when(userStoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(UserStoryNotFoundException.class,
				() -> userStoryService.updateUserStory(1L, new UserStoryRequestDto()));
	}

	@Test
	void deleteUserStory_storyExists() {
		UserStory story = getSampleUserStory();
		Mockito.when(userStoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(story));
		Mockito.doNothing().when(userStoryRepository).delete(Mockito.any());
		userStoryService.deleteUserStory(story.getId());
		Mockito.verify(userStoryRepository, Mockito.times(1)).delete(story);
	}

	@Test
	void deleteUserStory_storyDoesNotExists() {
		UserStory story = getSampleUserStory();
		Mockito.when(userStoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		userStoryService.deleteUserStory(story.getId());
		Mockito.verify(userStoryRepository, Mockito.times(0)).delete(story);
	}

	private UserStory getSampleUserStory() {
		UserStory userStory = new UserStory();
		userStory.setId(1L);
		userStory.setTitle("Title");
		userStory.setDescription("Description");
		userStory.setCompletionDate(LocalDate.now().plusDays(10));
		return userStory;
	}

}
