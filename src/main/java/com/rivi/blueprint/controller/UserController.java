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

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public User get(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User post(@RequestBody UserRequestDto user) {
		return userService.createUser(user);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
