package com.rivi.blueprint.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rivi.blueprint.enums.State;

@ExtendWith(MockitoExtension.class)
class StateConverterTest {

	@InjectMocks
	StateConverter stateConverter;

	@Test
	void convertToDatabaseColumn() {
		Integer id = stateConverter.convertToDatabaseColumn(State.IN_PROGRESS);
		assertNotNull(id);
		assertEquals(3, id);
	}

	@Test
	void convertToEntityAttribute() {
		State state = stateConverter.convertToEntityAttribute(2);
		assertNotNull(state);
		assertEquals(State.DEFINED, state);
	}

}
