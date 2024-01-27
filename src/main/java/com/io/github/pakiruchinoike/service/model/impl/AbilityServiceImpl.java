package com.io.github.pakiruchinoike.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.io.github.pakiruchinoike.domain.entity.Ability;
import com.io.github.pakiruchinoike.domain.repository.AbilityRepository;
import com.io.github.pakiruchinoike.exception.ServiceRuleException;
import com.io.github.pakiruchinoike.rest.dto.AbilityDTO;
import com.io.github.pakiruchinoike.service.AbilityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AbilityServiceImpl implements AbilityService{
    
    private final AbilityRepository repository;

    @Override
    @Transactional
    public Ability save(AbilityDTO dto) {
        Ability ability = convertFull(dto);

        return repository.save(ability);
    }

    @Override
    public Ability findById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    public List<Ability> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Ability> find(AbilityDTO dto) {
        Ability filter = convertFull(dto);

        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        
        Example<Ability> example = Example.of(filter, matcher);

        return repository.findAll(example);
    }

    @Override
    @Transactional
    public void update(AbilityDTO dto, Integer id) {
        Ability ability = convertFull(dto);

        repository.findById(id)
            .map(a -> {
                ability.setId(a.getId());
                repository.save(ability);
                return a;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.findById(id)
            .map(a -> {
                repository.delete(a);
                return a;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    private Ability convertFull(AbilityDTO dto) {
        return Ability.builder()
            .name(dto.getName())
            .level(dto.getLevel())
            .cost(dto.getCost())
            .damageAmount(dto.getDamageAmount())
            .damageType(dto.getDamageType())
            .checkType(dto.getCheckType())
            .description(dto.getDescription())
            .targetType(dto.getTargetType())
            .usedAttribute(dto.getUsedAttribute())
            .build();
    }

}
