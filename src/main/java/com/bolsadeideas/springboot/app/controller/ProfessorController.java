// ProfessorController.java
package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.entity.Professor;
import com.bolsadeideas.springboot.app.repository.GroupRepository;
import com.bolsadeideas.springboot.app.repository.ProfessorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfessorController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/createProfessor")
    public String showCreateProfessorForm(Model model) {
        List<Group> groups = groupRepository.findAll().stream()
                .filter(group -> !group.isAssigned())
                .collect(Collectors.toList());
        model.addAttribute("groups", groups);
        return "createProfessor";
    }
    
    @GetMapping("/asignarGrupo")
    public String asignarGrupo(Model model) {
        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("professors", professors);
        List<Group> groups = groupRepository.findAll().stream()
                .filter(group -> !group.isAssigned())
                .collect(Collectors.toList());
        model.addAttribute("groups", groups);
        return "asignarGrupo";
    }
    
    @GetMapping("/professors")
    public String listProfessors(Model model) {
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);

        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("professors", professors);
        return "listProfessors"; // Nombre de la plantilla HTML
    }

    @PostMapping("/asignarGrupo")
    public String asignarGrupo(@RequestParam("professorId") String professorId,
                               @RequestParam("groupIds") List<String> groupIds) {
        // Buscar el profesor por su ID
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor != null) {
            // Buscar los grupos seleccionados por sus IDs y asignarlos al profesor
            List<Group> selectedGroups = groupRepository.findAllById(groupIds);
            selectedGroups.forEach(group -> {
                professor.getGroupNames().add(group.getGroupName());
                group.setAssigned(true);
                groupRepository.save(group); // Guardar el grupo con el estado actualizado
            });
            professorRepository.save(professor); // Guardar el profesor con los grupos asignados
        }
        return "redirect:/index";
    }

    @PostMapping("/createProfessor")
    public String createProfessor(@RequestParam("name") String name,
                                  @RequestParam("email") String email) {
        Professor professor = new Professor();
        professor.setName(name);
        professor.setEmail(email);
        professorRepository.save(professor);
        return "redirect:/index";
    }
    
    @GetMapping("/editProfessor")
    public String showEditProfessorForm(@RequestParam("id") String id, Model model) {
        Professor professor = professorRepository.findById(id).orElse(null);
        List<Group> groups = groupRepository.findAll().stream()
                .filter(group -> !group.isAssigned() || professor.getGroupNames().contains(group.getGroupName()))
                .collect(Collectors.toList());
        model.addAttribute("professor", professor);
        model.addAttribute("groups", groups);
        return "editProfessor";
    }

    @PostMapping("/editProfessor")
    public String editProfessor(@RequestParam("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam(value = "groups", required = false) List<String> groupIds) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professor.setName(name);
            professor.setEmail(email);
            professor.setGroupNames(groupIds);
            professorRepository.save(professor);
        }
        return "redirect:/professors";
    }

    @GetMapping("/deleteProfessor")
    public String deleteProfessor(@RequestParam("id") String id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            List<Group> groups = groupRepository.findAllById(professor.getGroupNames());
            for (Group group : groups) {
                group.setAssigned(false);
                groupRepository.save(group);
            }
            professorRepository.delete(professor);
        }
        return "redirect:/professors";
    }
}
