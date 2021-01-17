package com.mpolec.student.project.controller;

import com.mpolec.student.project.entity.UserEntity;
import com.mpolec.student.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "home/loginPage";
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByLogin(principal.getName()));

        return "home/homePage";
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserEntity());

        return "home/registrationPage";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "home/registrationPage";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "redirect:/login";
    }
}
