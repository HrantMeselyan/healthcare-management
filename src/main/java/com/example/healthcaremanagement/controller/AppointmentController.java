package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.AppointmentImp.AppointmentService;
import com.example.healthcaremanagement.service.DoctorImpl.DoctorService;
import com.example.healthcaremanagement.service.PatientImpl.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/appointments")
@Controller
public class AppointmentController {
    private AppointmentService appointmentService;
    private PatientService patientService;
    private DoctorService doctorService;

    @GetMapping
    public String doctorPage(ModelMap modelMap) {
        modelMap.addAttribute("appointments", appointmentService.all());
        return "appointments";
    }

    @GetMapping("/add")
    public String appointmentAddPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("doctors", doctorService.all());
        modelMap.addAttribute("patients", patientService.all(currentUser));
        return "/addAppointment";
    }

    @PostMapping("/add")
    public String appointmentAdd(@ModelAttribute Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
