package com.example.demo.controller.v1;

import com.example.demo.Dto.AuthRequest;
import com.example.demo.Dto.AuthenticationResponse;
import com.example.demo.Dto.RegisterRequest;
import com.example.demo.component.TokenClass;
import com.example.demo.entity.AppUser;
import com.example.demo.serivces.EmailService;
import com.example.demo.serivces.ServiceImplement.JwtService;
import com.example.demo.serivces.ServiceInterface.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private  final UserServiceInterface UserService;
    private  final TokenClass serviceToken;
    private final EmailService emailService;
    public AuthenticationController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, UserDetailsService userDetailsService, UserServiceInterface userService, TokenClass serviceToken, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.UserService = userService;
        this.serviceToken = serviceToken;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            System.out.println(request.getUsername() + " : userName");
            System.out.println(request.getPassword() + " : password");

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            if (authenticate.isAuthenticated()) {
                String token = jwtService.generateToken(request.getUsername());
                AppUser user =UserService.GetUserByNumero(request.getUsername());
                AuthenticationResponse authRepo= new AuthenticationResponse(token,user.getPrenom(),user.getNom(),new ArrayList<>());
                return ResponseEntity.ok(authRepo);
            } else {
                throw new RuntimeException("Invalid login credentials");
            }

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
    @PostMapping("/register")
    public String SignUp(@RequestBody RegisterRequest request) {
        System.out.println("hhhhhhhhhhhhh");

        AppUser user = new AppUser();
        user.setNumero(request.getPhoneNumber());
        user.setNom(request.getLastName());
        user.setPrenom(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        this.UserService.AddNewUser(user);
        String subject = "Bienvenue sur l'application Orange Alert !";

        String message = String.format("""
Bonjour %s,

Nous sommes ravis de vous accueillir sur Orange Alert, notre application dédiée à la déclaration et au suivi de vos problèmes de réseau.

Votre compte a bien été créé, et vous pouvez dès à présent signaler tout dysfonctionnement que vous rencontrez.
Notre équipe est là pour vous accompagner et vous offrir une assistance rapide et efficace.

Bienvenue parmi nous,
L’équipe Orange Alert
""",request.getFirstName()+" "+request.getLastName());
        System.out.println(request.getEmail());
        emailService.sendEmail(request.getEmail().trim(), subject,message);
        return "signUP";
    }
    @GetMapping("/Verification/{email}/{numero}")
    public String Verification(@PathVariable String email,@PathVariable String numero) {
        boolean EmailIsExist = UserService.isEmailExists(email);
        boolean NumeroIsExist = UserService.isPhoneNumberExists(numero);
        if(EmailIsExist && NumeroIsExist){
            return "Email et numéro de téléphone existent déjà";
        }else {
            if(EmailIsExist){
                return "L'email existe déjà.";
            }else if(NumeroIsExist){
                return "Le numéro de téléphone existe déjà.";
            }else{
                return "Correct";
            }
        }

    }


}

