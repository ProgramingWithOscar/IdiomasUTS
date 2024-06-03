package com.bolsadeideas.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> findGroupById(String id) {
        return groupRepository.findById(id);
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public void deleteGroupById(String id) {
        groupRepository.deleteById(id);
    }
}
