package com.yrol.restplayground_1.controllers;


import com.yrol.restplayground_1.entitites.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("students")
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
    public Student getStudent(@PathVariable("id") int id) {
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

    /**
     * PostMapping and RequestBody
     * Use Postman to post data {firstName:"Elon", lastName:"Musk"}
     * http://localhost:8080/student/new
     * */
    @PostMapping(path = "student/new", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student newStudent(@RequestBody Student student) {
        return new Student(1, student.getFirstName(), student.getLastName());
    }

    /**
     * PatchMapping(partial update) with PathVariable and RequestBody
     * Use Postman to patch data {firstName:"Elon", lastName:"Musk"}
     * http://localhost:8080/student/update/23
     * */
    @PatchMapping(path = "student/update/{id}", consumes = "application/json")
    public Student updateStudent(@PathVariable("id") int id,  @RequestBody Student patchStudent) {
        Student student = new Student(id, "Van", "Damme");
        student.setFirstName(patchStudent.getFirstName());
        student.setLastName(patchStudent.getLastName());
        return student;
    }

    @DeleteMapping("student/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") int id) {

    }
}
