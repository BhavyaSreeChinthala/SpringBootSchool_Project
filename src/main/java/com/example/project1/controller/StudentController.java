package com.example.project1.controller;


import com.example.project1.entity.StudentEntity;
import com.example.project1.service.StudentServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/childrens")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity) {
        StudentEntity savedStudent = studentServiceImpl.saveStudent(studentEntity);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity studentEntity, @PathVariable Long id) {
        StudentEntity updatedStudent = studentServiceImpl.updateStudent(studentEntity, id);
        return ResponseEntity.ok(updatedStudent);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentEntity> deleteStudent(@PathVariable Long id) {
        StudentEntity deletedStudent = studentServiceImpl.deleteStudent(id);
        return ResponseEntity.ok(deletedStudent);
    }
}
