package com.example.healthcaremanagement.service.AppointmentImp;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> all() {
        return appointmentRepository.findAll();
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public void delete(int id) {
        appointmentRepository.deleteById(id);
    }

}
