package com.io.github.pakiruchinoike.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.io.github.pakiruchinoike.domain.entity.character.Character;
import com.io.github.pakiruchinoike.rest.dto.CharacterDTO;
import com.io.github.pakiruchinoike.service.model.CharacterService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
    
    @Autowired
    CharacterService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Character postCharacter(@RequestBody @Valid CharacterDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public Character getCharacter(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Character> getAllCharacter() {
        return service.findAll();
    }

    @GetMapping
    public List<Character> findCharacter(@RequestBody CharacterDTO dto) {
        return service.find(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public void putCharacter(@RequestBody CharacterDTO dto, @PathVariable Integer id) {
        service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCharacter(@PathVariable Integer id) {
        service.delete(id);
    }

}
