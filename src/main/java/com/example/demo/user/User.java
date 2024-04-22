package com.example.demo.user;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

}
