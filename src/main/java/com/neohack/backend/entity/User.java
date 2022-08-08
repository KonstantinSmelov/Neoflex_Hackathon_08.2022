package com.neohack.backend.entity;

import lombok.*;
import com.neohack.backend.model.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password", length = 1000)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private boolean isActive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), schema = "neohack")
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "last_activity")
    private LocalDate lastActivity;

}
