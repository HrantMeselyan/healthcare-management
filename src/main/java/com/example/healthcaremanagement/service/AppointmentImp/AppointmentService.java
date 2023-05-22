package com.example.healthcaremanagement.service.AppointmentImp;

import com.example.healthcaremanagement.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> all();

    void save(Appointment appointment);

    void delete(int id);
}
