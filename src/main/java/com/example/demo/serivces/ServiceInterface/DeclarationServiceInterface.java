package com.example.demo.serivces.ServiceInterface;

import com.example.demo.entity.Declaration;

import java.util.List;

public interface DeclarationServiceInterface {
     Declaration SaveDeclaration (Declaration declaration);
    Declaration UpdateDeclaration (Declaration declaration);
    List<Declaration> GetAllDeclaration ();
    Declaration GetDeclaration(Long id );

}
