package com.io.github.pakiruchinoike.service.model;

import java.util.List;

import com.io.github.pakiruchinoike.domain.entity.character.Character;
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
