package com.example.swe_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'user'")
    private String username;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'email@example.com'")
    private String email;

    private String password;
    private boolean enabled;
    private String status;
    private String role;

}
