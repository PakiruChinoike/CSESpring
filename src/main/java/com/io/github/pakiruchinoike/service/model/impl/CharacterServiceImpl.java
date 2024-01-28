package com.io.github.pakiruchinoike.service.model.impl;

import com.io.github.pakiruchinoike.rest.dto.CharacterDTO;
import com.io.github.pakiruchinoike.service.model.CharacterService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.io.github.pakiruchinoike.domain.entity.character.Attributes;
import com.io.github.pakiruchinoike.domain.entity.character.Character;
import com.io.github.pakiruchinoike.domain.entity.character.Class;
import com.io.github.pakiruchinoike.domain.entity.client.User;
import com.io.github.pakiruchinoike.domain.repository.AttributesRepository;
import com.io.github.pakiruchinoike.domain.repository.CharacterRepository;
import com.io.github.pakiruchinoike.domain.repository.ClassRepository;
import com.io.github.pakiruchinoike.domain.repository.UserRepository;
import com.io.github.pakiruchinoike.exception.ServiceRuleException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService{
    
    private final CharacterRepository repository;
    private final ClassRepository classRepository;
    private final AttributesRepository attributesRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Character save(CharacterDTO dto) {
        Character c = convertFull(dto);

        return repository.save(c);
    }

    @Override
    @Transactional
    public Character save(Character c) {
        return repository.save(c);
    }

    @Override
    @Transactional
    public Character updatePowerHealth(Character c) {
        Integer power = c.getPp();
        Integer health = c.getHp();
        Integer id = c.getId();

        return repository.updatePowerHealth(power, health, id);
    }

    @Override
    public Character findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    public List<Character> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Character> find(CharacterDTO dto) {
        Character filter = convertRaw(dto);

        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Character> example = Example.of(filter, matcher);

        return repository.findAll(example);
    }

    @Override
    @Transactional
    public void update(CharacterDTO dto, Integer id) {
        Character newCharacter = convertFull(dto);

        repository.findById(id)
            .map(c -> {
                newCharacter.setId(c.getId());
                repository.save(newCharacter);
                return c;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    @Override
    @Transactional
    public void delete(Integer id){
        repository.findById(id)
            .map(c -> {
                repository.delete(c);
                return c;
            }).orElseThrow(() -> new ServiceRuleException());
    }

    private Character convertRaw(CharacterDTO dto) {
        return Character.builder()
            .name(dto.getName())
            .level(dto.getLevel())
            .fortune(dto.getFortune())
            .hp(dto.getHp())
            .pp(dto.getPp())
            .ap(dto.getAp())
            .build();
    }

    private Character convertFull(CharacterDTO dto) {
        Integer attributesId = dto.getAttributes();
        Attributes attributes = attributesRepository.findById(attributesId)
                                .orElseThrow(() -> new ServiceRuleException());

        Integer classId = dto.getCharClass();
        Class charClass = classRepository.findById(classId)
                        .orElseThrow(() -> new ServiceRuleException());
        
        return Character.builder()
            .name(dto.getName())
            .level(dto.getLevel())
            .fortune(dto.getFortune())
            .hp(dto.getHp())
            .pp(dto.getPp())
            .ap(dto.getAp())
            .attributes(attributes)
            .charClass(charClass)
            .build();
        
    }

}
