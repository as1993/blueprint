package com.rivi.blueprint.service;

import java.util.List;

import com.rivi.blueprint.dto.TaskRequestDto;
import com.rivi.blueprint.entity.Task;

public interface ITaskService {

	Task addTask(Long userStoryId, TaskRequestDto task);

	void deleteTask(Long taskId);

	List<Task> getTasks();

}