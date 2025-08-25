package com.example.project1.service;


import com.example.project1.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    List<StudentEntity> getAllStudents();
    StudentEntity saveStudent(StudentEntity studentEntity);
    StudentEntity updateStudent(StudentEntity studentEntity, Long id);
    StudentEntity deleteStudent(Long id);
}

