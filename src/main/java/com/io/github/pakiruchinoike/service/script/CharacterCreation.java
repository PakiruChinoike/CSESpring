package com.io.github.pakiruchinoike.service.script;

import org.springframework.stereotype.Service;

import com.io.github.pakiruchinoike.domain.entity.Attributes;
import com.io.github.pakiruchinoike.domain.entity.Character;
import com.io.github.pakiruchinoike.domain.enums.Classname;
import com.io.github.pakiruchinoike.rest.dto.AttributesDTO;
import com.io.github.pakiruchinoike.rest.dto.CharCreationDTO;
import com.io.github.pakiruchinoike.service.model.CharacterService;
import com.io.github.pakiruchinoike.service.model.ClassService;
import com.io.github.pakiruchinoike.domain.entity.Class;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CharacterCreation {

    private final CharacterService characterService;
    private final ClassService classService;

    public Character createChar(CharCreationDTO dto) {
        String charName = dto.getName();
        AttributesDTO attributes = dto.getAttributes();
        Class charClass = classSelector(dto.getClassname());
        Attributes classAttributes = charClass.getAttributes();

        Attributes charAttributes = sumAttributes(classAttributes, attributes);

        return characterService.save(buildCharacter(charName, charClass, charAttributes));
    }

    private Class classSelector(Classname classname) {
        return classService.findByClassname(classname);
    }

    private Attributes sumAttributes(Attributes classAttributes, AttributesDTO dto) {
        Integer charisma = classAttributes.getCharisma() + dto.getCharisma();
        Integer constituion = classAttributes.getConstitution() + dto.getConstitution();
        Integer dexterity = classAttributes.getDexterity() + dto.getDexterity();
        Integer intelligence = classAttributes.getIntelligence() + dto.getIntelligence();
        Integer strenght = classAttributes.getStrenght() + dto.getStrenght();
        Integer wisdom = classAttributes.getWisdom() + dto.getWisdom();

        return Attributes.builder()
            .charisma(charisma)
            .constitution(constituion)
            .dexterity(dexterity)
            .intelligence(intelligence)
            .strenght(strenght)
            .wisdom(wisdom)
            .build();
    }

    private Character buildCharacter(String charName, Class charClass, Attributes charAttributes) {
        Integer level = 1;
        Integer hp = (charClass.getHp() + charAttributes.getConstitution()) * level;
        Integer pp = (charClass.getPp() + charAttributes.getIntelligence()) * level;
        Integer ap = (10 + charAttributes.getDexterity());
        Integer fortune = (charClass.getFortune() * charAttributes.getCharisma());

        return Character.builder()
            .name(charName)
            .charClass(charClass)
            .attributes(charAttributes)
            .level(1)
            .hp(hp)
            .pp(pp)
            .ap(ap)
            .fortune(fortune)
            .build();
    }

}
