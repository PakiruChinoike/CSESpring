package com.io.github.pakiruchinoike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.io.github.pakiruchinoike.domain.entity.Character;

public interface CharacterRepository extends JpaRepository<Character, Integer>{

    @Query(" UPDATE Character SET pp=:pp, hp=:hp WHERE id=:id ")
    public Character updatePowerHealth(@Param("pp") Integer pp, @Param("hp") Integer hp, @Param("id") Integer id);

}
