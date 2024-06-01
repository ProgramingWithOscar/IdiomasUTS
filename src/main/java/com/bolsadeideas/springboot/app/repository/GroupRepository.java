package com.bolsadeideas.springboot.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bolsadeideas.springboot.app.entity.Group;

public interface GroupRepository extends MongoRepository<Group, Long> {
}