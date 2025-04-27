package com.example.demo.serivces.ServiceImplement;

import com.example.demo.component.TokenClass;
import com.example.demo.entity.Declaration;
import com.example.demo.repository.DeclarationRepository;
import com.example.demo.serivces.ServiceInterface.DeclarationServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeclarationService implements DeclarationServiceInterface {
    private final DeclarationRepository declarationRepo ;
   private  final  TokenClass tokensService;



    public DeclarationService(DeclarationRepository declarationRepo, TokenClass tokensService) {

        this.declarationRepo = declarationRepo;


        this.tokensService = tokensService;
    }
    public Declaration SaveDeclaration (Declaration declaration){

        return declarationRepo.save(declaration);
    }
    public Declaration UpdateDeclaration (Declaration declaration){

        if (!declarationRepo.existsById(declaration.getId())) {
            throw new RuntimeException("Declaration not found");
        }
        return declarationRepo.save(declaration);
    }
    public List<Declaration> GetAllDeclaration (){

        return  declarationRepo.findByUserId(tokensService.getIdUserfromToken());
    }

    @Override
    public Declaration GetDeclaration(Long id) {
        Optional<Declaration> declarationOptional = declarationRepo.findById(id);
        Declaration dec = new Declaration();
        try {
            dec=declarationOptional.orElseThrow();
            return dec;
        }catch (Exception e){
            System.out.println("Id not found");
        }
        return dec;
    }
}
