package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.PatientImpl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/patients")
@Controller
public class PatientController {
    private PatientService patientService;

    @GetMapping
    public String patientsPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("patients",patientService.all(currentUser));
        return "/patients";
    }

    @GetMapping("/add")
    public String patientAddPage() {
        return "/addPatient";
    }

    @PostMapping("/add")
    public String patientAdd(@ModelAttribute Patient patient, @AuthenticationPrincipal CurrentUser currentUser) {
        patient.setUser(currentUser.getUser());
        patientService.save(patient,currentUser);
        return "redirect:/patients";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id) {
        patientService.delete(id);
        return "redirect:/patients";
    }
}
