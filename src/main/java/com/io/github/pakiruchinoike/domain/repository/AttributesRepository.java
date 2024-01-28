package com.io.github.pakiruchinoike.domain.repository;

import org.springframework.stereotype.Repository;

import com.io.github.pakiruchinoike.domain.entity.character.Attributes;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Integer>{
    
}
