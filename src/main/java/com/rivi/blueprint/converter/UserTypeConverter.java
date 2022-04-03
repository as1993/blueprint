package com.rivi.blueprint.converter;

import javax.persistence.AttributeConverter;

import com.rivi.blueprint.enums.UserType;

public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UserType type) {
		return type.getId();
	}

	@Override
	public UserType convertToEntityAttribute(Integer type) {
		return UserType.valueOf(type);
	}
}
