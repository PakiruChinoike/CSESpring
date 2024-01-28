package com.io.github.pakiruchinoike.rest.controller;

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

import com.io.github.pakiruchinoike.domain.entity.ability.Ability;
import com.io.github.pakiruchinoike.rest.dto.AbilityDTO;
import com.io.github.pakiruchinoike.service.model.AbilityService;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/ability")
public class AbilityConroller {
    
    @Autowired
    AbilityService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Ability postAbility(@RequestBody AbilityDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public Ability getAbility(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Ability> getAllAbility() {
        return service.findAll();
    }

    @GetMapping
    public List<Ability> findAbility(@RequestBody AbilityDTO dto) {
        return service.find(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public void putAbility(@RequestBody AbilityDTO dto, @PathVariable Integer id) {
        service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAbility(@PathVariable Integer id) {
        service.delete(id);
    }

}
