package com.bolsadeideas.springboot.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
// StudentController.java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.entity.Professor;
import com.bolsadeideas.springboot.app.entity.Student;
import com.bolsadeideas.springboot.app.repository.GroupRepository;
import com.bolsadeideas.springboot.app.repository.ProfessorRepository;
import com.bolsadeideas.springboot.app.repository.StudentRepository;
import com.bolsadeideas.springboot.app.service.StudentService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private StudentRepository repository;
    
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/home")
    public  String home(Model model) {
    	  List<Professor> professors = professorRepository.findAll();
          model.addAttribute("professors", professors);
        List<Group> groups = groupRepository.findAll(); // Obteniendo la lista de grupos desde la base de datos
        model.addAttribute("groups", groups);
    	return "home";
    }
    @GetMapping("/index")
    public String index() {
    	return "index";
    }
    @PostMapping("/import")
    public String importStudents(@RequestParam("file") MultipartFile file, 
                                 @RequestParam("group") String group) {
        try {
            studentService.saveStudentsFromExcel(file.getInputStream(), group);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return "home"; // Redirigir a una página de éxito
    }
    @GetMapping("/students")
    public String listStudents(Model model) {
    	 List<Group> groups = groupRepository.findAll(); // Obteniendo la lista de grupos desde la base de datos
         model.addAttribute("groups", groups);
        List<Student> students = repository.findAll();
        model.addAttribute("students", students);
        return "StudentsLits"; // Nombre de la plantilla HTML
    }
    


}
