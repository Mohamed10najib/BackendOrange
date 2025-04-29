package com.example.demo.repository;

import com.example.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
   AppUser findByNumero(String numero);
   AppUser findByEmail(String email);
   boolean existsByEmail(String email);

   boolean existsByNumero (String numero);

}
