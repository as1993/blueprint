package com.rivi.blueprint.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rivi.blueprint.enums.UserType;

@ExtendWith(MockitoExtension.class)
class UserTypeConverterTest {

	@InjectMocks
	UserTypeConverter userTypeConverter;

	@Test
	void convertToDatabaseColumn() {
		Integer id = userTypeConverter.convertToDatabaseColumn(UserType.DEVELOPER);
		assertNotNull(id);
		assertEquals(2, id);
	}

	@Test
	void convertToEntityAttribute() {
		UserType type = userTypeConverter.convertToEntityAttribute(1);
		assertNotNull(type);
		assertEquals(UserType.PRODUCT_MANAGER, type);
	}

}
