package com.example.project1.controller;


import com.example.project1.dto.AuthRequest;
import com.example.project1.entity.StudentEntity;
import com.example.project1.repository.StudentRepository;
import com.example.project1.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthRequest request) {
        String email = request.getEmail();
        String name = request.getName();

        List<StudentEntity> students = studentRepository.findByEmailAndName(email, name);
        if (!students.isEmpty()) {
            String token = jwtUtil.generateToken(email);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid credentials"));
        }
    }

}

