package com.example.healthcaremanagement.controller;


import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/doctors")
@Controller
public class DoctorController {
    @Value("${healthcare.upload.image.path}")
    private String imageUploadPath;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public String doctorPage(ModelMap modelMap) {
        List<Doctor> all = doctorRepository.findAll();
        modelMap.addAttribute("doctors", all);
        return "doctors";
    }

    @GetMapping("/add")
    public String doctorAdd() {
        return "addDoctor";
    }

    @PostMapping("/add")
    public String doctorAddPage(@ModelAttribute Doctor doctor, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/remove")
    public String doctorRemove(@RequestParam("id") int id){
       doctorRepository.deleteById(id);
       return "redirect:/doctors";
    }
}