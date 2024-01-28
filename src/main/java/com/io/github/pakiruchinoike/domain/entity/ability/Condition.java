package com.io.github.pakiruchinoike.domain.entity.ability;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Condition {
    
    private final CombatAmbient ambient;

    private Trigger trigger;

    private Ability conditionEffect;

    private Integer priorityLevel;

    private void triggers() {
        if (trigger.isTrigger()) {
           //uses Ability conditionEffect 
        }
    }

}
