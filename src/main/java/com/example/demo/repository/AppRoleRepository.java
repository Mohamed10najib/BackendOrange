package com.example.demo.repository;
import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppRoleRepository  extends JpaRepository<AppRole,Long> {
   AppRole findByRoleName(String roleName);
}
