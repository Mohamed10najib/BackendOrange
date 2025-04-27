package com.example.demo.repository;

import com.example.demo.entity.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationRepository  extends JpaRepository<Declaration,Long> {
    List<Declaration> findByUserId(Long userId);
}
