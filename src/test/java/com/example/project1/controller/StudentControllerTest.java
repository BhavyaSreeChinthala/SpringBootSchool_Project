package com.example.project1.controller;
import com.example.project1.entity.StudentEntity;
import com.example.project1.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        StudentEntity student = new StudentEntity();
        student.setName("Test");

        when(studentService.saveStudent(student)).thenReturn(student);

        ResponseEntity<StudentEntity> response = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void testGetAllStudents() {
        when(studentService.getAllStudents()).thenReturn(List.of(new StudentEntity()));

        List<StudentEntity> result = studentController.getAllStudents();

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateStudent() {
        StudentEntity student = new StudentEntity();
        student.setName("Updated");

        when(studentService.updateStudent(student, 1L)).thenReturn(student);

        ResponseEntity<StudentEntity> response = studentController.updateStudent(student, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody().getName());
    }

    @Test
    void testDeleteStudent() {
        StudentEntity student = new StudentEntity();
        student.setId(1L);

        when(studentService.deleteStudent(1L)).thenReturn(student);

        ResponseEntity<StudentEntity> response = studentController.deleteStudent(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }
}
