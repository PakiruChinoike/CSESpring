package com.io.github.pakiruchinoike.domain.entity.ability;

import java.util.List;

import com.io.github.pakiruchinoike.domain.entity.character.Character;
import com.io.github.pakiruchinoike.domain.entity.creature.Creature;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CombatAmbient {
    
    private static Integer round;
    private static Integer turn;

    private static Character character;
    private static List<Creature> allies;
    private static List<Creature> enemies;

    public Integer getRound() {
        return round;
    }

    public Integer getTurn() {
        return turn;
    }

}
