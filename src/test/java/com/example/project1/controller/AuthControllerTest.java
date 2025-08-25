package com.example.project1.controller;

import com.example.project1.dto.AuthRequest;
import com.example.project1.entity.StudentEntity;
import com.example.project1.repository.StudentRepository;
import com.example.project1.security.JwtUtil;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private JwtUtil jwtUtil;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAuthenticate_ValidCredentials_ReturnsToken() {
        AuthRequest request = new AuthRequest("test@example.com", "Test");

        StudentEntity student = new StudentEntity();
        student.setEmail("test@example.com");
        student.setName("Test");

        when(studentRepository.findByEmailAndName("test@example.com", "Test"))
                .thenReturn(List.of(student));
        when(jwtUtil.generateToken("test@example.com")).thenReturn("mockToken");

        ResponseEntity<Object> response = authController.authenticate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("token"));
        assertEquals("mockToken", body.get("token"));
    }

    @Test
    void testAuthenticate_InvalidCredentials_ReturnsUnauthorized() {
        AuthRequest request = new AuthRequest("wrong", "name");

        when(studentRepository.findByEmailAndName("wrong", "name"))
                .thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = authController.authenticate(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertEquals("Invalid credentials", body.get("error"));
    }

}
