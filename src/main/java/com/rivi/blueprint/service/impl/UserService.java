package com.rivi.blueprint.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.enums.UserType;
import com.rivi.blueprint.exception.UserNotFoundException;
import com.rivi.blueprint.repository.UserRepository;
import com.rivi.blueprint.service.IUserService;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + "does not exists!"));
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User createUser(UserRequestDto userDto) {
		User user = mapDtoToEntity(userDto);
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
	}

	private User mapDtoToEntity(UserRequestDto dto) {
		User user = new User();

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());

		if (dto.getUserTypeId() != null) {
			user.setType(UserType.valueOf(dto.getUserTypeId()));
		}

		return user;
	}
}
