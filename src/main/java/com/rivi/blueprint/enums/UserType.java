package com.rivi.blueprint.enums;

public enum UserType {
	PRODUCT_MANAGER(1), DEVELOPER(2);

	private Integer id;

	UserType(int i) {
		this.id = i;
	}

	public Integer getId() {
		return id;
	}

	public static UserType valueOf(Integer id) {
		for (UserType value : values()) {
			if (value.getId().equals(id)) {
				return value;
			}
		}
		return null;
	}
}
