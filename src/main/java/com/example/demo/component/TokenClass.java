package com.example.demo.component;

import com.example.demo.entity.AppUser;
import com.example.demo.serivces.ServiceImplement.JwtService;
import com.example.demo.serivces.ServiceInterface.UserServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
public class TokenClass {
    private final JwtService jwtService;
    private final UserServiceInterface userService;
    public TokenClass(JwtService jwtService, UserServiceInterface userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    private   String getTokenFromRequest() {
        // Get the HttpServletRequest from the current thread's request context
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // Get the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        // Check if the header exists and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Return the token by removing the "Bearer " prefix
            return authHeader.substring(7);
        }

        // Return null if the token is not present
        return null;
    }
    public  Long getIdUserfromToken(){
        String token = getTokenFromRequest();
        System.out.println("token service"+token);
        if (token == null) {
            throw new RuntimeException("Token not provided");
        }
        String numeroTel = jwtService.extractUsername(token);

        System.out.println("Extracted telephone: " + numeroTel);
        AppUser user =userService.GetUserByNumero(numeroTel);
        if (user == null) {
            throw new RuntimeException("user Not Fount");
        }
        return user.getId();
    }
    public  AppUser getUserfromToken(){
        String token = getTokenFromRequest();
        System.out.println("token service"+token);
        if (token == null) {
            throw new RuntimeException("Token not provided");
        }
        String numeroTel = jwtService.extractUsername(token);

        System.out.println("Extracted telephone: " + numeroTel);
        AppUser user =userService.GetUserByNumero(numeroTel);
        if (user == null) {
            throw new RuntimeException("user Not Fount");
        }
        return user;
    }
}
