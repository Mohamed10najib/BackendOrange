package com.example.demo.controller.v1;

import com.example.demo.serivces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @Autowired
    private EmailService emailService;
    @GetMapping
    public String getAllUsers() {
       // emailService.sendEmail("mohamed.najib.engineer@gmail.com", "Hello Mohamed", "Bonjour Mohamed");
        return "hi  mohamed";
    }
    @GetMapping("/AllAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsersV2() {
        return "I am a admin ";
    }
    @GetMapping("/AllUser")
    @PreAuthorize("hasRole('USER')")
    public String getForUser() {
        return "I am a user ";
    }
    @GetMapping("/auth/login")
    public String Login(){

        return "Login"  ;
    }
}
