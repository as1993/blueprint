package com.rivi.blueprint.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.enums.UserType;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;

	@Test
	void getUser_success() {
		User user = getSampleUser();
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		assertNotNull(userService.getUser(1L));
	}

	@Test
	void getUser_fail() {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
	}

	@Test
	void getAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(userService.getAllUsers());
	}

	@Test
	void createUser() {
		User user = getSampleUser();
		UserRequestDto dto = new UserRequestDto();
		BeanUtils.copyProperties(user, dto);
		dto.setUserTypeId(user.getType().getId());

		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

		User newUser = userService.createUser(dto);
		assertNotNull(newUser);
		assertEquals(dto.getEmail(), newUser.getEmail());
		assertEquals(dto.getUserTypeId(), newUser.getType().getId());
	}

	@Test
	void deleteUser_userExists() {
		User user = getSampleUser();
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(user));
		Mockito.doNothing().when(userRepository).delete(Mockito.any());
		userService.deleteUser(user.getId());
		Mockito.verify(userRepository, Mockito.times(1)).delete(user);
	}

	@Test
	void deleteUser_userDoesNotExists() {
		User user = getSampleUser();
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		userService.deleteUser(user.getId());
		Mockito.verify(userRepository, Mockito.times(0)).delete(user);
	}

	private User getSampleUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Test User");
		user.setType(UserType.DEVELOPER);
		return user;
	}
}
