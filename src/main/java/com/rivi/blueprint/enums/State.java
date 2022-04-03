package com.rivi.blueprint.enums;

public enum State {
	DISCOVERY(1), DEFINED(2), IN_PROGRESS(3), COMPLETED(4);

	private Integer id;

	State(int i) {
		this.id = i;
	}

	public Integer getId() {
		return id;
	}

	public static State valueOf(Integer id) {
		for (State value : values()) {
			if (value.getId().equals(id)) {
				return value;
			}
		}
		return null;
	}
}
