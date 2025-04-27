package com.example.demo.controller.v1;

import com.example.demo.Dto.DeclarationDto;
import com.example.demo.Mappers.DeclarationMapper;
import com.example.demo.component.TokenClass;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Declaration;

import com.example.demo.serivces.ServiceImplement.DeclarationService;
import com.example.demo.serivces.ServiceInterface.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DeclarationController {
    private DeclarationService declarationService ;
    private UserServiceInterface  userService ;
    private  final TokenClass serviceToken;
    public DeclarationController(DeclarationService declarationService, UserServiceInterface  userService, TokenClass serviceToken) {
        this.declarationService = declarationService;
        this.userService =userService;
        this.serviceToken = serviceToken;
    }
    @GetMapping("/signalements")
    public List<DeclarationDto> getAllDeclaration() {
        try {
            List<Declaration> declarations = declarationService.GetAllDeclaration();

            if (declarations == null || declarations.isEmpty()) {
                return new ArrayList<>();
            }

            return declarations.stream()
                    .map(DeclarationMapper::convertToDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Error occurred while fetching declarations: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching declarations", e);
        }
    }


    @GetMapping("/signalements/{id}")
    public DeclarationDto getDeclaration(@PathVariable("id") Long id){
        DeclarationDto declarationDto =DeclarationMapper.convertToDTO( declarationService.GetDeclaration(id));
        return declarationDto;
    }
    @PostMapping("/signalement")
    public DeclarationDto saveDeclaration(@RequestBody DeclarationDto declarationDto) {
        System.out.println("Trying to save declaration...");
        System.out.println("al"+declarationDto.getAltitude()+"");
        Declaration declaration = DeclarationMapper.convertToEntity(declarationDto);
        declaration.setDateDeCreation(LocalDateTime.now());
        declaration.setDateDeResolution(LocalDateTime.now());

        AppUser user = serviceToken.getUserfromToken();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        declaration.setUser(user);

        Declaration savedDeclaration = declarationService.SaveDeclaration(declaration);

        return DeclarationMapper.convertToDTO(savedDeclaration);
    }


}
