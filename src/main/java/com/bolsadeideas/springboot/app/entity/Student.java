package com.bolsadeideas.springboot.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
    
    @Id
    private String id;
    private String name;
    private String idStudent;
    private String level;
    private String program;
    private String note1;
    private String note2;
    private String note3;
    private String finalGrade;  // Corregido el typo de "finalGrande" a "finalGrade"
    private String remarks;
    private String group;
    private String professor;
    private String subgroup;

    public Student() {
    }

    public Student(String id, String name, String idStudent, String level, String program, String note1, String note2,
                   String note3, String finalGrade, String remarks, String group, String professor, String subgroup) {
        super();
        this.id = id;
        this.name = name;
        this.idStudent = idStudent;
        this.level = level;
        this.program = program;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.finalGrade = finalGrade;
        this.remarks = remarks;
        this.group = group;
        this.professor = professor;
        this.subgroup = subgroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getNote1() {
    	double nota = Double.parseDouble(note1);
    	nota = Math.round(nota * 100) /100;
        return String.valueOf(nota);
    }

    public void setNote1(String note1) {
    	
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
    	double nota = Double.parseDouble(note1);
    	nota = Math.round(nota * 100) /100;
        return String.valueOf(nota);
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getFinalGrade() { 
    	double nota = Double.parseDouble(note1);
    	nota = Math.round(nota * 100) /100;
        return String.valueOf(nota);
    }

    public void setFinalGrade(String finalGrade) {  // Corregido el typo de "finalGrande" a "finalGrade"
        this.finalGrade = finalGrade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }
}
