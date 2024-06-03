package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.service.GroupService;

import java.util.List;

@Controller
public class GruposController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/grupos")
    public String index(Model model) {
        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "grupos";
    }

    @GetMapping("/gruposList")
    public String gruposList(Model model) {
        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "gruposList";
    }

    @PostMapping("/grupos")
    public String createGroup(@RequestParam("groupName") String groupName,
                              @RequestParam("studentCount") int studentCount,
                              @RequestParam("modality") String modality,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("englishLevel") String englishLevel,
                              @RequestParam("scheduleStart") String scheduleStart,
                              @RequestParam("scheduleEnd") String scheduleEnd) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setStudentCount(studentCount);
        group.setModality(modality);
        group.setStartDate(startDate);
        group.setEndDate(endDate);
        group.setEnglishLevel(englishLevel);
        group.setScheduleStart(scheduleStart);
        group.setScheduleEnd(scheduleEnd);
        
        groupService.saveGroup(group);
        
        return "redirect:/gruposList";
    }

    @GetMapping("/grupos/edit/{id}")
    public String editGroupForm(@PathVariable String id, Model model) {
        Group group = groupService.findGroupById(id).orElse(null);
        if (group != null) {
            model.addAttribute("group", group);
            return "editGroup";
        }
        return "redirect:/gruposList";
    }

    @PostMapping("/grupos/edit/{id}")
    public String updateGroup(@PathVariable String id,
                              @RequestParam("groupName") String groupName,
                              @RequestParam("studentCount") int studentCount,
                              @RequestParam("modality") String modality,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("englishLevel") String englishLevel,
                              @RequestParam("scheduleStart") String scheduleStart,
                              @RequestParam("scheduleEnd") String scheduleEnd) {
        Group group = groupService.findGroupById(id).orElse(null);
        if (group != null) {
            group.setGroupName(groupName);
            group.setStudentCount(studentCount);
            group.setModality(modality);
            group.setStartDate(startDate);
            group.setEndDate(endDate);
            group.setEnglishLevel(englishLevel);
            group.setScheduleStart(scheduleStart);
            group.setScheduleEnd(scheduleEnd);
            groupService.saveGroup(group);
        }
        return "redirect:/gruposList";
    }

    @GetMapping("/grupos/delete/{id}")
    public String deleteGroup(@PathVariable String id) {
        groupService.deleteGroupById(id);
        return "redirect:/gruposList";
    }
}
