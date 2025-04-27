package com.example.demo.entity;

import com.example.demo.enums.StatusEnum;
import com.example.demo.enums.TypeProblemeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Declaration {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id ;

    public void setId(Long id) {
        Id = id;
    }

    public String getAdressComplet() {
        return adressComplet;
    }

    public void setAdressComplet(String adressComplet) {
        this.adressComplet = adressComplet;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    private TypeProblemeEnum TypeDeProbleme;
    private String Description;
    private StatusEnum Status;
    private LocalDateTime DateDeCreation;
    private String adressComplet;
    private String ville;

    public String getTypeDeExact() {
        return typeDeExact;
    }

    public void setTypeDeExact(String typeDeExact) {
        this.typeDeExact = typeDeExact;
    }

    private  String typeDeExact ;

    public String getNatureProbleme() {
        return NatureProbleme;
    }

    public void setNatureProbleme(String natureProbleme) {
        NatureProbleme = natureProbleme;
    }

    private  String NatureProbleme ;
    private LocalDateTime DateDeResolution;

    private String Reponse ;

    private Double Altitude;
    private  Integer debit;
    private Double longitude;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public TypeProblemeEnum getTypeDeProbleme() {
        return TypeDeProbleme;
    }

    public void setTypeDeProbleme(TypeProblemeEnum typeDeProbleme) {
        TypeDeProbleme = typeDeProbleme;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public StatusEnum getStatus() {
        return Status;
    }

    public void setStatus(StatusEnum status) {
        Status = status;
    }

    public LocalDateTime getDateDeCreation() {
        return DateDeCreation;
    }

    public void setDateDeCreation(LocalDateTime dateDeCreation) {
        DateDeCreation = dateDeCreation;
    }

    public LocalDateTime getDateDeResolution() {
        return DateDeResolution;
    }

    public void setDateDeResolution(LocalDateTime dateDeResolution) {
        DateDeResolution = dateDeResolution;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String reponse) {
        Reponse = reponse;
    }

    public Double getAltitude() {
        return Altitude;
    }

    public void setAltitude(Double altitude) {
        Altitude = altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AppUser user ;

}
