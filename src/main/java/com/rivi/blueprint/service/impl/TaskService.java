package com.rivi.blueprint.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rivi.blueprint.dto.TaskRequestDto;
import com.rivi.blueprint.entity.Task;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.exception.UserStoryNotFoundException;
import com.rivi.blueprint.repository.TaskRepository;
import com.rivi.blueprint.repository.UserRepository;
import com.rivi.blueprint.repository.UserStoryRepository;
import com.rivi.blueprint.service.ITaskService;

@Service
public class TaskService implements ITaskService {

	private final UserStoryRepository userStoryRepository;
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskService(UserStoryRepository userStoryRepository, TaskRepository taskRepository,
			UserRepository userRepository) {
		this.userStoryRepository = userStoryRepository;
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Task addTask(Long userStoryId, TaskRequestDto taskDto) {

		Task task = mapDtoToEntity(taskDto);
		return userStoryRepository.findById(userStoryId).map(s -> {
			task.setStory(s);
			return taskRepository.save(task);
		}).orElseThrow(() -> new UserStoryNotFoundException("User Story with id " + userStoryId + " does not exists!"));
	}

	@Override
	public void deleteTask(Long taskId) {
		Optional<Task> task = taskRepository.findById(taskId);

		if (task.isPresent()) {
			taskRepository.delete(task.get());
		}
	}

	@Override
	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}

	private Task mapDtoToEntity(TaskRequestDto dto) {
		Task task = new Task();

		if (dto.getTitle() != null) {
			task.setTitle(dto.getTitle());
		}

		if (dto.getDescription() != null) {
			task.setDescription(dto.getDescription());
		}

		if (dto.getUserId() != null) {
			User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);
			task.setAssignee(user);
		}

		return task;
	}
}