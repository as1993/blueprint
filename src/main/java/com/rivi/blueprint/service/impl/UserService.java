package com.rivi.blueprint.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rivi.blueprint.dto.UserRequestDto;
import com.rivi.blueprint.entity.User;
import com.rivi.blueprint.enums.UserType;
import com.rivi.blueprint.exception.BadRequestException;
import com.rivi.blueprint.exception.DuplicateUserException;
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
		validateRequest(userDto);
		User user = mapDtoToEntity(userDto);
		return userRepository.save(user);
	}

	private void validateRequest(UserRequestDto userDto) {
		if (!StringUtils.hasLength(userDto.getName()) || !StringUtils.hasLength(userDto.getEmail())) {
			throw new BadRequestException("Please provide a valid user name and email");
		}

		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		if (user.isPresent()) {
			throw new DuplicateUserException(String.format("User with email %s already exists", userDto.getEmail()));
		}
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

