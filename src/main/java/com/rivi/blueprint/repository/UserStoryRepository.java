package com.rivi.blueprint.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rivi.blueprint.entity.UserStory;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
}
