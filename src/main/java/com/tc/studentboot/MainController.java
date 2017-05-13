/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.studentboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/student")

public class MainController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/create")
    @ResponseBody
    public String create(String id, String name, String age, String email) {

        try {
            Student student = new Student(id, name, age, email);
            studentRepository.save(student);

        } catch (Exception ex) {
            return "Error creating the student: " + ex.toString();
        }
        return "Student succesfully created with id = " + id;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        try {
            Student student = new Student(id);
            studentRepository.delete(student);
        } catch (Exception ex) {
            return "Error deleting the student:" + ex.toString();
        }
        return "Student succesfully deleted!";
    }

    @GetMapping("/findbyid")
    @ResponseBody
    public String getById(String id) {
        String studentName;
        try {
            Student student = studentRepository.findById(id);
            studentName = String.valueOf(student.getName());
        } catch (Exception ex) {
            return "Student not found";
        }
        return "The name of the student is: " + studentName;
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateUser(String id, String name, String age, String email) {
        try {
            Student student = studentRepository.findById(id);
            student.setId(id);
            student.setEmail(email);
            student.setName(name);
            student.setAge(age);
            studentRepository.save(student);
        } catch (Exception ex) {
            return "Error updating the student: " + ex.toString();
        }
        return "Student succesfully updated!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {

        return studentRepository.findAll();
    }
}
