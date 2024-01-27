package com.io.github.pakiruchinoike.service;

import java.util.List;

import com.io.github.pakiruchinoike.domain.entity.Character;
import com.io.github.pakiruchinoike.rest.dto.CharacterDTO;

public interface CharacterService {
    
    Character save(CharacterDTO dto);
    Character save(Character c);
    Character updatePowerHealth(Character c);
    Character findById(Integer id);
    List<Character> findAll();
    List<Character> find(CharacterDTO dto);
    void update(CharacterDTO dto, Integer id);
    void delete(Integer id);

}
