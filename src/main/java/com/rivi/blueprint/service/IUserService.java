package com.rivi.blueprint.service;

import java.util.List;

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;

public interface IUserService {

	User createUser(UserRequestDto story);

	void deleteUser(Long userStoryId);

	List<User> getAllUsers();

	User getUser(Long id);

}
