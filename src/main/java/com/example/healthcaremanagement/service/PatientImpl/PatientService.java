package com.example.healthcaremanagement.service.PatientImpl;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.security.CurrentUser;

import java.util.List;

public interface PatientService {
    List<Patient> all(CurrentUser currentUser);

    void save(Patient patient,CurrentUser currentUser);

    void delete(int id);
}
