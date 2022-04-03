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

import com.rivi.blueprint.dto.UserStoryRequestDto;
import com.rivi.blueprint.entity.UserStory;
import com.rivi.blueprint.exception.CustomGlobalExceptionHandler;
import com.rivi.blueprint.service.impl.UserStoryService;

@ExtendWith(MockitoExtension.class)
class UserStoryControllerTest {

	private static final String USER_STORIES_URL = "/api/v1/userstories";
	@Mock
	UserStoryService userStoryService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		UserStoryController userStoryController = new UserStoryController(userStoryService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userStoryController)
				.setControllerAdvice(CustomGlobalExceptionHandler.class).build();
	}

	@Test
	void get() throws Exception {
		Mockito.doReturn(new UserStory()).when(userStoryService).getUserStory(Mockito.anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.get(USER_STORIES_URL + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getAll() throws Exception {
		Mockito.doReturn(new ArrayList<>()).when(userStoryService).getAllUserStories();
		this.mockMvc.perform(MockMvcRequestBuilders.get(USER_STORIES_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void post() throws Exception {
		Mockito.doReturn(new UserStory()).when(userStoryService)
				.createUserStory(Mockito.any(UserStoryRequestDto.class));
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(USER_STORIES_URL).contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void put() throws Exception {
		Mockito.doReturn(new UserStory()).when(userStoryService).updateUserStory(Mockito.anyLong(),
				Mockito.any(UserStoryRequestDto.class));
		this.mockMvc.perform(MockMvcRequestBuilders.put(USER_STORIES_URL + "/1").contentType(MediaType.APPLICATION_JSON)
				.content("{}")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void delete() throws Exception {
		Mockito.doNothing().when(userStoryService).deleteUserStory(Mockito.anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.delete(USER_STORIES_URL + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
