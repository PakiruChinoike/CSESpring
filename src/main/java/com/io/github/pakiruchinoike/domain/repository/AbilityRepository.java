package com.io.github.pakiruchinoike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.pakiruchinoike.domain.entity.ability.Ability;

public interface AbilityRepository extends JpaRepository<Ability, Integer>{
}
