package com.example.demo.filter;

import com.example.demo.serivces.ServiceImplement.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            System.out.println("Extracted username: {}"+ username);
        } else {
            System.out.println("Authorization header is missing or invalid");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("user name is :"+username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("user name 2 is :"+userDetails.getUsername());
            System.out.println("is valid  :"+jwtService.isTokenValid(jwt, userDetails.getUsername()));

            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Authentication set for user: {}"+username);
            } else {
                System.out.println("Invalid JWT token for user: {}"+ username);
            }
        }

        // Ensure that the filter chain is invoked at the end
        filterChain.doFilter(request, response);
    }

}
