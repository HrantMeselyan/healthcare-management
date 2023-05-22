package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.DoctorImpl.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/doctors")
@Controller
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public String doctorPage(ModelMap modelMap) {
        modelMap.addAttribute("doctors", doctorService.all());
        return "doctors";
    }

    @GetMapping("/add")
    public String doctorAdd() {
        return "addDoctor";
    }

    @PostMapping("/add")
    public String doctorAddPage(@ModelAttribute Doctor doctor, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        doctorService.saveDoctor(doctor, multipartFile, currentUser);
        return "redirect:/doctors";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id) {
        doctorService.delete(id);
        return "redirect:/doctors";
    }
}
