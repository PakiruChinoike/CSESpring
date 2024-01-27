package com.io.github.pakiruchinoike.domain.entity.ability;

import com.io.github.pakiruchinoike.domain.enums.TriggerType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Trigger {
    
    private final CombatAmbient ambient;

    private TriggerType triggerType;

    private Integer turnValue;

    private Integer roundValue;

    protected boolean isTrigger() {
        switch (triggerType) {
            case PASSIVE:
                return true;
            
            case TURN_BASED:
                if (turnValue<=ambient.getTurn()) {
                    return true;
                } else {
                    return false;
                }

            case ROUND_BASED:
                if (roundValue<=ambient.getRound()) {
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }
    }

}
