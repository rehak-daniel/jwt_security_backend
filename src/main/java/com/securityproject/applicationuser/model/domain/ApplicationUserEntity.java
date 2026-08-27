package com.securityproject.applicationuser.model.domain;
import java.util.UUID;

import com.securityproject.applicationuser.model.enums.Role;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_user")
public class ApplicationUserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role = Role.ROLE_USER;

    @Column(name = "active", nullable = false)
    private boolean active = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
