package com.bolsadeideas.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bolsadeideas.springboot.app.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
	public Optional<Student> findById(String idStudent);

}
