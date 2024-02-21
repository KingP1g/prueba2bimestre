package com.saew.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saew.dto.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private List<Student> students = new ArrayList<>();

    // Constructor to initialize some sample data
    public StudentsController() {
        // Sample data
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Aiden Blank");
        student1.setEmail("aiden@epn.com");
        student1.setGPA(10);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Lu Armas");
        student2.setEmail("lu@epn.com");
        student2.setGPA(8);

        students.add(student1);
        students.add(student2);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/byName")
    public List<Student> getStudentByName(@RequestParam String name) {
        return students.stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateProduct(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        students.removeIf(student -> student.getId() == id);
        return ResponseEntity.noContent().build();
    }
}
