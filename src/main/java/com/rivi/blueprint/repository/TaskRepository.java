package com.rivi.blueprint.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rivi.blueprint.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
