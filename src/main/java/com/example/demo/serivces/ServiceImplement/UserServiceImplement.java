package com.example.demo.serivces.ServiceImplement;

import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppRoleRepository;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.serivces.ServiceInterface.UserServiceInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImplement implements UserServiceInterface, UserDetailsService {
    private   AppUserRepository  UserRepo;
    private AppRoleRepository RoleRepo;
    public  UserServiceImplement (AppUserRepository userRepo,AppRoleRepository roleRepo){
         this.UserRepo =userRepo;
         this.RoleRepo=roleRepo;
    }
    @Override
    public AppUser GetUserByNumero(String numero) {
        String cleanedNumero = numero.trim();
        AppUser user = UserRepo.findByNumero(cleanedNumero);

        if (user == null) {
            throw new NoSuchElementException("User not found with numero: " + cleanedNumero);
        }

        System.out.println(user.getNumero());
        return user;
    }

    @Override
    public AppUser GetUserByEmail(String email) {
        String cleanedEmail = email.trim();
        AppUser user = UserRepo.findByEmail(cleanedEmail);

        if (user == null) {
            throw new NoSuchElementException("User not found with email: " + cleanedEmail);
        }

        System.out.println(user.getEmail());
        return user;

    }
    public boolean isEmailExists(String email) {
        return UserRepo.existsByEmail(email);
    }


    public boolean isPhoneNumberExists(String numero) {
        return UserRepo.existsByNumero(numero);
    }

    @Override
    public AppRole GetRoleByName(String RoleName) {
        AppRole role = null ;
        try {
            role =RoleRepo.findByRoleName(RoleName);
            if(role==null){
                throw  new Exception("Role Not found");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return  role ;
    }

    @Override
    public AppUser AddNewUser(AppUser user) {
       try{
           AppUser userApp =  UserRepo.save(user);
           return userApp;
       }catch(Exception  e){
           System.out.println("error : "+ e.getMessage());
       }
        return null;
    }

    @Override
    public AppRole AddNewRole(AppRole role) {
        try{
            AppRole appRole =  RoleRepo.save(role);
            return appRole;
        }catch(Exception  e){
            System.out.println("error : "+ e.getMessage());
        }
        return null;
    }

    @Override
    public void AddRoleToUser(String roleName, String numero) {

        try {
            AppRole role = GetRoleByName(roleName);
            AppUser appuser = GetUserByNumero(numero);

            appuser.getRole().add(role);

        }catch(Exception e){
            System.out.println("l'error :"+e.getMessage());
        }
    }

    @Override
    public List<AppUser> ListeUser() {
        return UserRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return GetUserByNumero(username);
    }
}
