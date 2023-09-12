package com.example.springsecurity.entity;

import com.example.springsecurity.controller.Role;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="spring_security")
@SequenceGenerator(name="user_gen",sequenceName="user_seq", initialValue = 1, allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    /*@JoinTable(name = "spring-security",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))*/
    private List<Role> roles;

    public User(Long id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
