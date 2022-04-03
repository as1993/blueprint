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

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.exception.CustomGlobalExceptionHandler;
import com.rivi.blueprint.service.impl.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	private static final String USERS_URL = "/api/v1/users";
	@Mock
	UserService userService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		UserController userController = new UserController(userService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setControllerAdvice(CustomGlobalExceptionHandler.class).build();
	}

	@Test
	void getAll() throws Exception {
		Mockito.doReturn(new ArrayList<>()).when(userService).getAllUsers();
		this.mockMvc.perform(MockMvcRequestBuilders.get(USERS_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void get() throws Exception {
		Mockito.doReturn(new User()).when(userService).getUser(Mockito.anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.get(USERS_URL + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void post() throws Exception {
		Mockito.doReturn(new User()).when(userService).createUser(Mockito.any(UserRequestDto.class));
		this.mockMvc
				.perform(MockMvcRequestBuilders.post(USERS_URL).contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void delete() throws Exception {
		Mockito.doNothing().when(userService).deleteUser(Mockito.anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.delete(USERS_URL + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
