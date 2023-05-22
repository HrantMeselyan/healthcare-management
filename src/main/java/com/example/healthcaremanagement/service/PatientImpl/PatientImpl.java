package com.example.healthcaremanagement.service.PatientImpl;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.entity.Usertype;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> all(CurrentUser currentUser) {
        List<Patient> all;
        if (currentUser.getUser().getType() == Usertype.ADMIN) {
            all = patientRepository.findAll();
        } else {
            all = patientRepository.findAllByUser_Id(currentUser.getUser().getId());
        }
        return all;
    }

    @Override
    public void save(Patient patient, CurrentUser currentUser) {
        patient.setUser(currentUser.getUser());
        patientRepository.save(patient);
    }

    @Override
    public void delete(int id) {
        patientRepository.deleteById(id);
    }
}
