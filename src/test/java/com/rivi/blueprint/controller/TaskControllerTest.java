package com.rivi.blueprint.controller;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rivi.blueprint.dto.TaskRequestDto;
import com.rivi.blueprint.entity.Task;
import com.rivi.blueprint.exception.CustomGlobalExceptionHandler;
import com.rivi.blueprint.service.impl.TaskService;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

	private static final String TASKS_URL = "/api/v1/tasks";
	@Mock
	TaskService taskService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		TaskController taskController = new TaskController(taskService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskController)
				.setControllerAdvice(CustomGlobalExceptionHandler.class).build();
	}

	@Test
	void get() throws Exception {
		Mockito.doReturn(new ArrayList<>()).when(taskService).getTasks();
		this.mockMvc.perform(MockMvcRequestBuilders.get(TASKS_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void post() throws Exception {
		Mockito.doReturn(new Task()).when(taskService).addTask(Mockito.anyLong(), Mockito.any(TaskRequestDto.class));
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(TASKS_URL + "/1").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void delete() throws Exception {
		Mockito.doNothing().when(taskService).deleteTask(Mockito.anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.delete(TASKS_URL + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
