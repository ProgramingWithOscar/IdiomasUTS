package com.bolsadeideas.springboot.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bolsadeideas.springboot.app.entity.Admin;


public interface AdminRepository extends MongoRepository<Admin, String> {

	public Admin findByEmail(String email);

}
