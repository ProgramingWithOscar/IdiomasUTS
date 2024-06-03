// GroupRepository.java
package com.bolsadeideas.springboot.app.repository;

import com.bolsadeideas.springboot.app.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    Group findByGroupName(String groupName);
}
