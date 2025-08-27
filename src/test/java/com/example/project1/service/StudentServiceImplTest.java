package com.example.project1.service;

import com.example.project1.entity.StudentEntity;
import com.example.project1.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        StudentEntity student = new StudentEntity();
        when(studentRepository.save(student)).thenReturn(student);

        StudentEntity result = studentService.saveStudent(student);
        assertEquals(student, result);
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(new StudentEntity()));
        assertEquals(1, studentService.getAllStudents().size());
    }

    @Test
    void testUpdateStudent() {
        StudentEntity existing = new StudentEntity();
        existing.setId(1L);
        existing.setName("Old");

        StudentEntity updated = new StudentEntity();
        updated.setName("New");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(any())).thenReturn(existing);

        StudentEntity result = studentService.updateStudent(updated, 1L);
        assertEquals("New", result.getName());
    }

    @Test
    void testDeleteStudent() {
        StudentEntity student = new StudentEntity();
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentEntity result = studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).delete(student);
        assertEquals(1L, result.getId());
    }
}
