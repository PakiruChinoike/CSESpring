package com.io.github.pakiruchinoike.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.io.github.pakiruchinoike.domain.entity.Ability;
import com.io.github.pakiruchinoike.domain.entity.Attributes;
import com.io.github.pakiruchinoike.domain.entity.Character;
import com.io.github.pakiruchinoike.domain.entity.Weakness;
import com.io.github.pakiruchinoike.domain.enums.CheckType;
import com.io.github.pakiruchinoike.domain.enums.Damagetype;
import com.io.github.pakiruchinoike.domain.enums.TargetType;
import com.io.github.pakiruchinoike.domain.enums.UsedAttribute;
import com.io.github.pakiruchinoike.rest.dto.CombatLogDTO;
import com.io.github.pakiruchinoike.service.CharacterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Combat {

    private final CharacterService characterService;
    private final CombatLogDTO combatLog;
    
    public List<CombatLogDTO> startAbility(Ability ability, List<Character> characters, Integer target, Character user) {
        combatLog.setUserName(user.getName());
        combatLog.setAbilityDescription(ability.getDescription());
        combatLog.setAbilityName(ability.getName());

        TargetType targetType = ability.getTargetType();
        Character character = characters.get(target);

        switch (targetType) {
            case SINGLE_FOE: {
                List<CombatLogDTO> combatLogList = new ArrayList<>();
                combatLogList.add(abilitySingle(ability, character, user));
                CombatLogDTO.roundValue = CombatLogDTO.roundValue+1;
                return combatLogList;
            }
            case SINGLE_ALLY: {
                List<CombatLogDTO> combatLogList = new ArrayList<>();
                combatLogList.add(abilitySingle(ability, character, user));
                CombatLogDTO.roundValue = CombatLogDTO.roundValue+1;
                return combatLogList;
            }
            case MULTIPLE_FOE: {
                CombatLogDTO.roundValue = CombatLogDTO.roundValue+1;
                return abilityMultiple(ability, characters, user);
            }
            case MULTIPLE_ALLY: {
                CombatLogDTO.roundValue = CombatLogDTO.roundValue+1;
                return abilityMultiple(ability, characters, user);
            }
            default: {
                return null;
            }
        }
    }

    private Integer selectAttribute(Attributes charAttributes, UsedAttribute usedAttribute) {
        switch (usedAttribute) {
            case CHARISMA:
                return charAttributes.getCharisma();
        
            case CONSTITUTION:
                return charAttributes.getConstitution();

            case DEXTERITY:
                return charAttributes.getDexterity();

            case INTELLIGENCE:
                return charAttributes.getIntelligence();

            case STRENGHT:
                return charAttributes.getStrenght();

            case WISDOM:
                return charAttributes.getWisdom();

            default:
                return null;
        }
    }

    private boolean isWeak(Damagetype damageType, List<Weakness> weaknessess) {
        for(int i = 0; i<weaknessess.size(); i++) {
            if (damageType == weaknessess.get(i).getWeakness()) {
                combatLog.setWasWeak(true);
                return true;
            }
        }

        combatLog.setWasWeak(false);
        return false;
    }

    private boolean check(Integer attributeValue, Integer difficulty) {
        Integer checkValue = (int)(Math.random() * 20) + attributeValue;
        boolean check = checkValue>=difficulty;

        combatLog.setValueRolled(checkValue);
        return check;
    }

    private Integer rollDamage(Ability ability, Character user) {
        Integer damageAmount = ability.getDamageAmount();
        Integer attributeValue = selectAttribute(user.getAttributes(), ability.getUsedAttribute());
        
        Integer damage = (int)(Math.random() * damageAmount - 1) + attributeValue;
        if(isWeak(ability.getDamageType(), user.getWeaknessess())) {
            damage = damage*2;
        }

        combatLog.setDamageCaused(damage);
        return damage;
    }

    private Integer rollHeal(Ability ability, Character user) {
        Integer healAmount = ability.getDamageAmount();
        Integer heal = (int)(Math.random() * healAmount) + 1;

        combatLog.setDamageCaused(heal);
        return heal;
    }

    private boolean isSuccess(CheckType checkType, Integer userAttribute, Integer targetAttribute, Integer difficulty, Integer ap) {
        switch (checkType) {
            case SAVING_THROW:
                return check(targetAttribute, difficulty);

            case ABILITY_CHECK:
                return check(userAttribute, ap);

            case PROFICIENCY_CHECK:
                return check(userAttribute, difficulty);

            case NONE:
                return true;

            default:
                return false;  
        }
    }

    private CombatLogDTO abilitySingle(Ability ability, Character target, Character user) {
        combatLog.setTargetName(target.getName());

        UsedAttribute attribute = ability.getUsedAttribute();
        TargetType targetType = ability.getTargetType();
        CheckType checkType = ability.getCheckType();

        Integer userAttribute = selectAttribute(user.getAttributes(), attribute);
        Integer targetAttribute = selectAttribute(target.getAttributes(), attribute);

        boolean success;

        switch (targetType) {
            case SINGLE_ALLY: {
                switch (checkType) {
                    case NONE: 
                        success = true;
                        break;

                    default:
                        success = false;
                        break;
                }

                if(success) {
                    Integer heal = rollHeal(ability, user);  
                    if (heal<0) {
                        heal = 0;
                    }        
                    target.setHp(target.getHp()+heal);
                    characterService.updatePowerHealth(user);

                    Integer cost = ability.getCost();
                    user.setPp(user.getPp()-cost);
                    characterService.updatePowerHealth(user);

                    combatLog.setTargetHp(target.getHp());
                    combatLog.setUserPp(user.getPp());
                } 
            }
                break;
        
            case SINGLE_FOE: {
                success = isSuccess(checkType, userAttribute, targetAttribute, ability.getDifficulty(), target.getAp());

                if(success) {
                    Integer damage = rollDamage(ability, user); 
                    if (damage<0) {
                        damage = 0;
                    }
                    target.setHp(target.getHp()-damage);
                    characterService.updatePowerHealth(target);

                    Integer cost = ability.getCost();
                    user.setPp(user.getPp()-cost);
                    characterService.updatePowerHealth(user);

                    combatLog.setTargetHp(target.getHp());
                    combatLog.setUserPp(user.getPp());
                }
                break;
            }

            default:
                break;
        }

        return combatLog;
    }

    private List<CombatLogDTO> abilityMultiple(Ability ability, List<Character> targets, Character user) {
        UsedAttribute attribute = ability.getUsedAttribute();
        TargetType targetType = ability.getTargetType();
        CheckType checkType = ability.getCheckType();

        Integer userAttribute = selectAttribute(user.getAttributes(), attribute);

        List<CombatLogDTO> combatLogList = new ArrayList<CombatLogDTO>();

        switch (targetType) {
            case MULTIPLE_ALLY:
            targets.stream()
                .map(t -> {
                    Integer targetAttribute = selectAttribute(t.getAttributes(), attribute);

                    boolean success = isSuccess(checkType, userAttribute, targetAttribute, ability.getDifficulty(), t.getAp());

                    if(success) {
                        Integer heal = rollHeal(ability, user);  
                        if (heal<0) {
                            heal = 0;
                        }        
                        t.setHp(t.getHp()+heal);
                        characterService.updatePowerHealth(user);
    
                        Integer cost = ability.getCost();
                        user.setPp(user.getPp()-cost);
                        characterService.updatePowerHealth(user);
    
                        combatLog.setTargetName(t.getName());
                        combatLog.setTargetHp(t.getHp());
                        combatLog.setUserPp(user.getPp());

                        combatLogList.add(combatLog);
                    }

                    return null;
                });
                    break;
        
            case MULTIPLE_FOE:
            targets.stream()
            .map(t -> {
                Integer targetAttribute = selectAttribute(t.getAttributes(), attribute);

                boolean success = isSuccess(checkType, userAttribute, targetAttribute, ability.getDifficulty(), t.getAp());

                if(success) {
                    Integer damage = rollDamage(ability, user);  
                    if (damage<0) {
                        damage = 0;
                    }        
                    t.setHp(t.getHp()-damage);
                    characterService.updatePowerHealth(user);

                    Integer cost = ability.getCost();
                    user.setPp(user.getPp()-cost);
                    characterService.updatePowerHealth(user);

                    combatLog.setTargetName(t.getName());
                    combatLog.setTargetHp(t.getHp());
                    combatLog.setUserPp(user.getPp());

                    combatLogList.add(combatLog);
                }

                return null;
            });
                break;

            default:
                break;
        }

        return null;
    }

}
