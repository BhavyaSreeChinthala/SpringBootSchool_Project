package com.example.project1.security;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void testGenerateAndValidateToken() {
        String email = "test@example.com";
        String token = jwtUtil.generateToken(email);

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
        assertEquals(email, jwtUtil.getEmailFromToken(token));
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "abc.def.ghi";
        assertFalse(jwtUtil.validateToken(invalidToken));
    }
}
