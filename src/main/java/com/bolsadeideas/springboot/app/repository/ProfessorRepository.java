package com.bolsadeideas.springboot.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bolsadeideas.springboot.app.entity.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
}