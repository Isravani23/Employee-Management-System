package com.example.entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;         // for login
    private String password;         // stored as encrypted (hashed)
    private boolean enabled = true;  // is account active?

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<EmployeeRole> roles;
}
