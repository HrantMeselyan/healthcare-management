package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.Usertype;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.MainImpl.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;


    @GetMapping("/")
    private String main(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        return "index";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("profilePic") String imageName) throws IOException {
        return mainService.getImage(imageName);
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getType() == Usertype.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getType() == Usertype.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
