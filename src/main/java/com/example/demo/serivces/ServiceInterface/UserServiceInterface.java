package com.example.demo.serivces.ServiceInterface;

import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;

import java.util.List;

public interface UserServiceInterface {
    AppUser GetUserByNumero  (String Numero);
    AppUser GetUserByEmail  (String email);
    AppRole GetRoleByName  (String RoleName);

    AppUser AddNewUser (AppUser user);
    boolean isEmailExists(String email);
    boolean isPhoneNumberExists(String numero);

    AppRole AddNewRole (AppRole role);
    void  AddRoleToUser(String roleName , String   numero);
    List<AppUser> ListeUser ();


}
