package com.rivi.blueprint.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rivi.blueprint.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
