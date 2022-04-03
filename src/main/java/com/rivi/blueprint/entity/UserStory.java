package com.rivi.blueprint.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rivi.blueprint.converter.StateConverter;
import com.rivi.blueprint.enums.State;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "story")
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title = "";
	private String description = "";
	private LocalDate completionDate;
	@OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();
	@NonNull
	@Convert(converter = StateConverter.class)
	private State state = State.DISCOVERY;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User assignee;

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;

		for (Task b : tasks) {
			b.setStory(this);
		}
	}
}
