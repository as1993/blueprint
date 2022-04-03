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

import com.rivi.blueprint.dto.TaskRequestDto;
import com.rivi.blueprint.entity.Task;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.entity.UserStory;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.exception.UserStoryNotFoundException;
import com.rivi.blueprint.repository.TaskRepository;
import com.rivi.blueprint.repository.UserRepository;
import com.rivi.blueprint.repository.UserStoryRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	UserStoryRepository userStoryRepository;
	@Mock
	TaskRepository taskRepository;
	@Mock
	UserRepository userRepository;
	@InjectMocks
	TaskService taskService;

	@Test
	void getAllTasks() {
		Mockito.when(taskRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(taskService.getTasks());
	}

	@Test
	void addTask_success() {
		Task task = getSampleTask();
		TaskRequestDto dto = new TaskRequestDto();
		BeanUtils.copyProperties(task, dto);

		Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		Mockito.when(userStoryRepository.findById(Mockito.any(Long.class)))
				.thenReturn(Optional.of(getSampleUserStory()));

		Task saved = taskService.addTask(1L, dto);

		assertNotNull(saved);
		assertEquals(dto.getTitle(), saved.getTitle());
	}

	@Test
	void addTask_storyNotExists() {
		Task task = getSampleTask();
		TaskRequestDto dto = new TaskRequestDto();
		BeanUtils.copyProperties(task, dto);

		Mockito.when(userStoryRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

		assertThrows(UserStoryNotFoundException.class, () -> taskService.addTask(1L, dto));
	}

	@Test
	void addTask_userNotExists() {

		Task task = getSampleTask();
		User user = getSampleUser();
		task.setAssignee(user);

		TaskRequestDto dto = new TaskRequestDto();
		BeanUtils.copyProperties(task, dto);
		dto.setUserId(user.getId());

		Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> taskService.addTask(1L, dto));
	}

	@Test
	void deleteTask_taskExists() {
		Task task = getSampleTask();
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(task));
		Mockito.doNothing().when(taskRepository).delete(Mockito.any());
		taskService.deleteTask(task.getId());
		Mockito.verify(taskRepository, Mockito.times(1)).delete(task);
	}

	@Test
	void deleteTask_taskDoesNotExists() {
		Task task = getSampleTask();
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		taskService.deleteTask(task.getId());
		Mockito.verify(taskRepository, Mockito.times(0)).delete(task);
	}

	private Task getSampleTask() {
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Title");
		task.setDescription("Description");
		task.setAssignee(new User());
		return task;
	}

	private User getSampleUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Test User");
		return user;
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
