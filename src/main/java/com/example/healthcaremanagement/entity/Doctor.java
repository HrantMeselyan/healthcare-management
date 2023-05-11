package com.example.healthcaremanagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    private int id;

    private String surname;

    private String email;

    private String speciality;

    private String phoneNumber;

    private String profilePic;
}
