package com.example.mozispring.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="felhasznalok")
public class MyAppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="felhasznalonev")
    private String username;
    @Column(name="jelszo")
    private String password;
    @Column(name="szerepkor")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
