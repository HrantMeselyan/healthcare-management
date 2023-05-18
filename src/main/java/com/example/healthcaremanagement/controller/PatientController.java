package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/patients")
@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public String patientsPage(ModelMap modelMap) {
        List<Patient> all = patientRepository.findAll();
        modelMap.addAttribute("patients", all);
        return "/patients";
    }

    @GetMapping("/add")
    public String patientAddPage() {
        return "/addPatient";
    }

    @PostMapping("/add")
    public String patientAdd(@ModelAttribute Patient patient, @AuthenticationPrincipal CurrentUser currentUser) {
        patient.setUser(currentUser.getUser());
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
