package com.bolsadeideas.springboot.app.service;

// StudentService.java
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Student;
import com.bolsadeideas.springboot.app.excelReader.ExcelReader;
import com.bolsadeideas.springboot.app.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExcelReader excelReader;

    public void saveStudentsFromExcel(InputStream fileInputStream, String group, String professor) throws IOException {
        List<Student> students = excelReader.readExcel(fileInputStream);

        for (Student student : students) {
            student.setGroup(group);
            student.setProfessor(professor);
            student.setSubgroup(determineSubgroup(student)); // Implementar la lógica para determinar el subgrupo
        }

        studentRepository.saveAll(students);
    }

    private String determineSubgroup(Student student) {
        // Implementa la lógica para determinar el subgrupo según tus reglas de negocio
        // Por ejemplo, basado en el nivel o en las notas
        return "subgroup1"; // Esto es solo un ejemplo
    }
}
