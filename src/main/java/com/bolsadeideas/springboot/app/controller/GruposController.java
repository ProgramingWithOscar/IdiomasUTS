package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.app.entity.Group;
import com.bolsadeideas.springboot.app.service.GroupService;

@Controller
public class GruposController {
    
    @Autowired
    private GroupService groupService; // Asegúrate de tener el servicio GroupService autowired

    @GetMapping("/grupos")
    public String index(Model model) {
        return "grupos";
    }

    @PostMapping("/grupos")
    public String createGroup(@RequestParam("groupName") String groupName,
                              @RequestParam("studentCount") int studentCount,
                              @RequestParam("modality") String modality) {
        // Crea un nuevo objeto Group con los datos del formulario
        Group group = new Group();
        group.setGroupName(groupName);
        group.setStudentCount(studentCount);
        group.setModality(modality);
        
        // Guarda el grupo en la base de datos
        groupService.saveGroup(group);
        
        // Redirige a la página principal después de crear el grupo
        return "redirect:/index";
    }
}
