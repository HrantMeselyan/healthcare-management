package com.example.healthcaremanagement.service.DoctorImpl;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorImpl implements DoctorService {
    @Value("${healthcare.upload.image.path}")
    private String imageUploadPath;

    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> all() {
        return doctorRepository.findAll();
    }

    @Override
    public void saveDoctor(Doctor doctor, MultipartFile multipartFile, CurrentUser currentUser) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        doctor.setUser(currentUser.getUser());
        doctorRepository.save(doctor);
    }

    @Override
    public void delete(int id) {
        doctorRepository.deleteById(id);
    }
}
