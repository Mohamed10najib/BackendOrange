package com.example.demo.Mappers;

import com.example.demo.Dto.DeclarationDto;
import com.example.demo.entity.Declaration;

public class DeclarationMapper {
    public static DeclarationDto convertToDTO(Declaration declaration) {
        DeclarationDto dto = new DeclarationDto();
        dto.setId(declaration.getId());
        dto.setDescription(declaration.getDescription());
        dto.setDateDeCreation(declaration.getDateDeCreation());
        dto.setDateDeResolution(declaration.getDateDeResolution());
        dto.setReponse(declaration.getReponse());
        dto.setTypeDeProbleme(declaration.getTypeDeProbleme());
        dto.setAltitude(declaration.getAltitude());
        dto.setLongitude(declaration.getLongitude());
        dto.setStatus(declaration.getStatus());
        dto.setAdressComplet(declaration.getAdressComplet());
        dto.setDebit(declaration.getDebit());
        dto.setVille(declaration.getVille());
        dto.setNatureProbleme(declaration.getNatureProbleme());
        dto.setTypeDeExact(declaration.getTypeDeExact());
        dto.setDebit(declaration.getDebit());
        if (declaration.getUser() != null) {
            dto.setUserId(declaration.getUser().getId());
        }

        return dto;
    }
    public static Declaration convertToEntity(DeclarationDto declarationDto) {
        Declaration declaration = new Declaration();

        declaration.setDescription(declarationDto.getDescription());
        declaration.setDateDeCreation(declarationDto.getDateDeCreation());
        declaration.setDateDeResolution(declarationDto.getDateDeResolution());
        declaration.setReponse(declarationDto.getReponse());
        declaration.setTypeDeProbleme(declarationDto.getTypeDeProbleme());
        declaration.setAltitude(declarationDto.getAltitude());
        declaration.setLongitude(declarationDto.getLongitude());
        declaration.setStatus(declarationDto.getStatus());
        declaration.setNatureProbleme(declarationDto.getNatureProbleme());
        declaration.setVille(declarationDto.getVille());
        declaration.setAdressComplet(declarationDto.getAdressComplet());
        declaration.setTypeDeExact(declarationDto.getTypeDeExact());
        declaration.setDebit(declarationDto.getDebit());



        return declaration;
    }
}