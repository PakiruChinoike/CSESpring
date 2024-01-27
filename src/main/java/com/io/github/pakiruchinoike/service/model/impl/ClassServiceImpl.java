package com.io.github.pakiruchinoike.service.model.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.io.github.pakiruchinoike.domain.repository.AbilityRepository;
import com.io.github.pakiruchinoike.domain.repository.AttributesRepository;
import com.io.github.pakiruchinoike.domain.repository.ClassRepository;
import com.io.github.pakiruchinoike.exception.ServiceRuleException;
import com.io.github.pakiruchinoike.domain.entity.Ability;
import com.io.github.pakiruchinoike.domain.entity.Attributes;
import com.io.github.pakiruchinoike.domain.entity.Class;
import com.io.github.pakiruchinoike.domain.enums.Classname;
import com.io.github.pakiruchinoike.rest.dto.ClassDTO;
import com.io.github.pakiruchinoike.service.model.ClassService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService{
    
    private final ClassRepository repository;
    private final AttributesRepository attributesRepository;
    private final AbilityRepository abilityRepository;

    @Override
    @Transactional
    public Class save(ClassDTO dto) {
        Class c = convertFull(dto);

        return repository.save(c);
    }

    @Override
    public Class findById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    public Class findByClassname(Classname classname) {
        return repository.findByClassname(classname.name())
            .orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    public List<Class> findAll() {
        return repository.findAll();
    }

    public List<Class> find(ClassDTO dto) {
        Class filter = convertRaw(dto);

        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        
        Example<Class> example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

    @Override
    @Transactional
    public void update(ClassDTO dto, Integer id) {
        Class newClass = convertFull(dto);
       
        repository.findById(id)
            .map(c -> {
                newClass.setId(c.getId());
                repository.save(newClass);
                return c;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.findById(id)
            .map(c -> {
                repository.delete(c);
                return c;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    @Transactional
    public Class createClass(Class c) {
        Attributes attributes = c.getAttributes();
        attributesRepository.save(attributes);

        List<Ability> classAbilities = c.getAbilities();
        abilityRepository.saveAll(classAbilities);

        return repository.save(c);
    }

    private Class convertFull(ClassDTO dto) {
        Integer attributesId = dto.getAttributes();
        Attributes attributes = attributesRepository.findById(attributesId)
                                .orElseThrow(() -> new ServiceRuleException());

        return Class.builder()
                .name(dto.getName())
                .fortune(dto.getFortune())
                .hp(dto.getHp())
                .pp(dto.getPp())
                .classname(dto.getClassname())
                .attributes(attributes)
                .build();
    }

    private Class convertRaw(ClassDTO dto) {
        return Class.builder()
                .name(dto.getName())
                .fortune(dto.getFortune())
                .hp(dto.getHp())
                .pp(dto.getPp())
                .classname(dto.getClassname())
                .build();
    }

}
