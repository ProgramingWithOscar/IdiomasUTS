package com.bolsadeideas.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.repository.GroupRepository;

@Service
public class GroupService {
    
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
}
