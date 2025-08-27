package com.example.project1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Schema(name = "Student", description = "Details about a student in the school")
@Data
@Entity
@Table(name = "childrens")

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Name of the Student", example = "Bhavya")
    @Column(nullable = false)
    private String name;

    @Schema(description = "Email of the Student", example = "bhavya@gmail.com")
    @Column(nullable = false)
    private String email;

    @Schema(description = "Mobile number of the Student", example = "9963921536")
    @Column(name = "Mobile", nullable = false)
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

