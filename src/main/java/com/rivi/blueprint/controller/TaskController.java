package com.rivi.blueprint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rivi.blueprint.dto.TaskRequestDto;
import com.rivi.blueprint.entity.Task;
import com.rivi.blueprint.service.ITaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

	private final ITaskService taskService;

	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Task> get() {
		return taskService.getTasks();
	}

	@PostMapping("{storyId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Task post(@PathVariable Long storyId, @RequestBody TaskRequestDto task) {
		return taskService.addTask(storyId, task);
	}

	@DeleteMapping("{taskId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
	}

}
