package com.rivi.blueprint.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rivi.blueprint.converter.UserTypeConverter;
import com.rivi.blueprint.enums.UserType;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String email;
	@NonNull
	@Convert(converter = UserTypeConverter.class)
	private UserType type = UserType.DEVELOPER;
}
