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

import com.io.github.pakiruchinoike.domain.entity.character.Class;
import com.io.github.pakiruchinoike.rest.dto.ClassDTO;
import com.io.github.pakiruchinoike.service.model.ClassService;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    
    @Autowired
    ClassService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Class postClass(@RequestBody @Valid ClassDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public Class createClass(@RequestBody @Valid Class c) {
        return service.createClass(c);
    }

    @GetMapping("/{id}")
    public Class getClass(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Class> getAllClass() {
        return service.findAll();
    }

    @GetMapping
    public List<Class> findClass(@RequestBody ClassDTO dto) {
        return service.find(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public void putClass(@RequestBody @Valid ClassDTO dto, @PathVariable Integer id) {
        service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteClass(@PathVariable Integer id) {
        service.delete(id);
    }

}
