package com.example.healthcaremanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Usertype type;
}
