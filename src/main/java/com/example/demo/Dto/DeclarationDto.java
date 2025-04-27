package com.example.demo.Dto;

import com.example.demo.enums.StatusEnum;
import com.example.demo.enums.TypeProblemeEnum;

import java.time.LocalDateTime;
import java.util.Date;

public class DeclarationDto {
    private Long id;
    private  String NatureProbleme ;

    public String getTypeDeExact() {
        return typeDeExact;
    }

    public void setTypeDeExact(String typeDeExact) {
        this.typeDeExact = typeDeExact;
    }

    private String typeDeExact ;
    private String description;

    public LocalDateTime getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public LocalDateTime getDateDeResolution() {
        return dateDeResolution;
    }

    public void setDateDeResolution(LocalDateTime dateDeResolution) {
        this.dateDeResolution = dateDeResolution;
    }

    private LocalDateTime dateDeCreation;
    private LocalDateTime dateDeResolution;
    private String reponse;
    private TypeProblemeEnum typeDeProbleme;
    private Double altitude;
    private String adressComplet;
    private String ville;
    private  Integer debit;

    public String getNatureProbleme() {
        return NatureProbleme;
    }

    public void setNatureProbleme(String natureProbleme) {
        NatureProbleme = natureProbleme;
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

    private Double longitude;
    private StatusEnum status;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }






    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public TypeProblemeEnum getTypeDeProbleme() {
        return typeDeProbleme;
    }

    public void setTypeDeProbleme(TypeProblemeEnum typeDeProbleme) {
        this.typeDeProbleme = typeDeProbleme;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
