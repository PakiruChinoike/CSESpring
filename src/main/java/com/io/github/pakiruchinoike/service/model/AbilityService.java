package com.io.github.pakiruchinoike.service.model;

import java.util.List;

import com.io.github.pakiruchinoike.domain.entity.ability.Ability;
import com.io.github.pakiruchinoike.rest.dto.AbilityDTO;

public interface AbilityService {
 
    Ability save(AbilityDTO dto);
    Ability findById(Integer id);
    List<Ability> findAll();
    List<Ability> find(AbilityDTO dto);
    void update(AbilityDTO dto, Integer id);
    void delete(Integer id);

}
