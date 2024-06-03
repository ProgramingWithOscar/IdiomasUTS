package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.app.entity.Admin;
import com.bolsadeideas.springboot.app.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AterrizajeController {
	
	@Autowired
	private AdminRepository adminRepository;
	


	@GetMapping("/")
	public String index(Model model) {
		return "idiomasuts";
	}
	
	@GetMapping("/login")
	public String loginAdm(Model model) {
		return "loginUA";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
	    Admin admin = adminRepository.findByEmail(email);

	    if (admin != null && admin.getPassword().equals(password)) {
	        // Si es un administrador, redirigir al panel de administrador
	        session.setAttribute("isAdmin", true);
	        return "index";
	    
	    } else {
	        session.setAttribute("msg", "Correo o contraseña incorrecta. Verifica por favor");
	        return "loginUA"; // Página de inicio de sesión general
	    }
	}

}
