package com.rivi.blueprint.converter;

import javax.persistence.AttributeConverter;

import com.rivi.blueprint.enums.State;

public class StateConverter implements AttributeConverter<State, Integer> {

	@Override
	public Integer convertToDatabaseColumn(State status) {
		return status.getId();
	}

	@Override
	public State convertToEntityAttribute(Integer state) {
		return State.valueOf(state);
	}
}
