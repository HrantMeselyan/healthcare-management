package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/appointments")
@Controller
public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;


    @GetMapping
    public String doctorPage(ModelMap modelMap) {
        List<Appointment> all = appointmentRepository.findAll();
        modelMap.addAttribute("appointments", all);
        return "appointments";
    }

    @GetMapping("/add")
    public String patientAddPage(ModelMap modelMap) {
        List<Doctor> allDoctors = doctorRepository.findAll();
        List<Patient> allPatients = patientRepository.findAll();
        modelMap.addAttribute("doctors",allDoctors);
        modelMap.addAttribute("patients",allPatients);
        return "/addAppointment";
    }

    @PostMapping("/add")
    public String patientAdd(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
