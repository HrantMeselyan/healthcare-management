package com.example.healthcaremanagement.service.DoctorImpl;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DoctorService {
    List<Doctor> all();

    void saveDoctor(Doctor doctor, MultipartFile multipartFile, CurrentUser currentUser) throws IOException;

    void delete(int id);
}
