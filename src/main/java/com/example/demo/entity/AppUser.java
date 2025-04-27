package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AppUser implements UserDetails{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;
    private String nom;
    private  String prenom ;
    private String email;

    private String numero;
    private  String password ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                .map(r -> (GrantedAuthority) r::getRoleName)
                .collect(Collectors.toSet());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return numero;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private  List<Declaration> listeDeclarations ;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AppRole> role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Declaration> getListeDeclarations() {
        return listeDeclarations;
    }

    public void setListeDeclarations(List<Declaration> listeDeclarations) {
        this.listeDeclarations = listeDeclarations;
    }

    public List<AppRole> getRole() {
        return role;
    }

    public void setRole(List<AppRole> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}

