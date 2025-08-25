package com.example.project1.service;


import com.example.project1.entity.StudentEntity;
import com.example.project1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }
    @Override
    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }
    @Override
    public StudentEntity updateStudent(StudentEntity updateStudent, Long id){
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with ID:" +id));
        student.setName(updateStudent.getName());
        student.setEmail(updateStudent.getEmail());
        student.setMobile(updateStudent.getMobile());
        return studentRepository.save(student);
    }
    @Override
    public StudentEntity deleteStudent(Long id){
        StudentEntity student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with ID:" +id));
        studentRepository.delete(student);
        return student;
    }
}

