package com.io.github.pakiruchinoike.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.io.github.pakiruchinoike.domain.entity.character.Attributes;
import com.io.github.pakiruchinoike.domain.repository.AttributesRepository;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributesController {
    
    @Autowired
    AttributesRepository repository; 

    @GetMapping("/{id}")
    public Attributes getAttributes(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "{object.not-found}"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Attributes postAttributes(@RequestBody @Valid Attributes attributes) {
        return repository.save(attributes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAttributes(@PathVariable Integer id) {
        repository.findById(id)
            .map(a -> {
                repository.delete(a);
                return a;
            }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "{object.not-found}"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public void putAttributes(@RequestBody Attributes attributes, @PathVariable Integer id) {
        repository.findById(id)
            .map(a -> {
                attributes.setId(a.getId());
                repository.save(attributes);
                return a;
            }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "{object.not-found}"));
    }

    @GetMapping
    public List<Attributes> find(@RequestBody Attributes filter) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<Attributes> example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

}
