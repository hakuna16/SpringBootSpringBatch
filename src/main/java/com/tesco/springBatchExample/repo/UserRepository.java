package com.tesco.springBatchExample.repo;

import org.springframework.data.repository.Repository;

import com.tesco.springBatchExample.model.User;

public interface UserRepository extends Repository<User, String> {

	public User save(User entity);
}
