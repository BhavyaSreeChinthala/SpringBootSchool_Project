package com.example.project1.controller;


import com.example.project1.entity.StudentEntity;
import com.example.project1.service.StudentServiceImpl;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student API", description = "Operations related to student management")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/childrens")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity) {
        StudentEntity savedStudent = studentServiceImpl.saveStudent(studentEntity);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    @Operation(summary = "Update student by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity studentEntity, @PathVariable Long id) {
        StudentEntity updatedStudent = studentServiceImpl.updateStudent(studentEntity, id);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentEntity> deleteStudent(@PathVariable Long id) {
        StudentEntity deletedStudent = studentServiceImpl.deleteStudent(id);
        return ResponseEntity.ok(deletedStudent);
    }
}
