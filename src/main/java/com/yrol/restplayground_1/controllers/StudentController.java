package com.yrol.restplayground_1.controllers;


import com.yrol.restplayground_1.entitites.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    /**
     * GetMapping
     * http://localhost:8080/student/
     * */
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "James", "Webb");
        return student;
    }

    /**
     * GetMapping with PathVariable
     * http://localhost:8080/student/2
     * */
    @GetMapping("student/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = new Student(id, "John", "Cena");
        return student;
    }

    /**
     * GetMapping returns a list of students
     * http://localhost:8080/students
     * */
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Cena"));
        students.add(new Student(2, "Rodney", "Lui"));
        students.add(new Student(3, "Aaron", "Chang"));
        return students;
    }


    /**
     * GetMapping with RequestParams
     * http://localhost:8080/student/unique?id=1&firstname=James&lastname=Bond
     * */
    @GetMapping("student/unique")
    public Student getUniqueStudent(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname) {
        return new Student(id, firstname, lastname);
    }
}
