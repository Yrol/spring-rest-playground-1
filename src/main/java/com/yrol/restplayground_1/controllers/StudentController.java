package com.yrol.restplayground_1.controllers;


import com.yrol.restplayground_1.entitites.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * GetMapping with ResponseEntity
     * http://localhost:8080/student/student
     * */
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "James", "Webb");

        // ok() by default return 200
        return ResponseEntity.ok(student);
    }

    /**
     * GetMapping with ResponseEntity and custom value in the response header
     * http://localhost:8080/student/1/token
     * */
    @GetMapping("/{id}/token")
    public ResponseEntity<Student> getStudentWithToken(@PathVariable("id") int id) {
        Student student =  new Student(id, "Aaron", "Silva");

        return ResponseEntity.ok()
                .header("token", "abc12356")
                .body(student);
    }

    /**
     * GetMapping with PathVariable
     * http://localhost:8080/student/2
     * */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        Student student = new Student(id, "John", "Cena");
        return student;
    }

    /**
     * GetMapping returns a list of students
     * http://localhost:8080/student/all
     * */
    @GetMapping("/all")
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
    @GetMapping("/unique")
    public Student getUniqueStudent(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname) {
        return new Student(id, firstname, lastname);
    }

    /**
     * PostMapping and RequestBody
     * Use Postman to post data {firstName:"Elon", lastName:"Musk"}
     * http://localhost:8080/student/new
     * */
    @PostMapping(path = "/new", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> newStudent(@RequestBody Student newStudent) {

        Student student = new Student(1, newStudent.getFirstName(), newStudent.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /**
     * PatchMapping(partial update) with PathVariable and RequestBody
     * Use Postman to patch data {firstName:"Elon", lastName:"Musk"}
     * http://localhost:8080/student/update/23
     * */
    @PatchMapping(path = "/update/{id}", consumes = "application/json")
    public Student updateStudent(@PathVariable("id") int id,  @RequestBody Student patchStudent) {
        Student student = new Student(id, "Van", "Damme");
        student.setFirstName(patchStudent.getFirstName());
        student.setLastName(patchStudent.getLastName());
        return student;
    }


    /**
     * http://localhost:8080/student/23
     * */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") int id) {

    }
}
