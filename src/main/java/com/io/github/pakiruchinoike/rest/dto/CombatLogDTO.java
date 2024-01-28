package com.io.github.pakiruchinoike.rest.dto;

import javax.annotation.ManagedBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
public class CombatLogDTO {

    //TARGET INFORMATION
    private Integer targetHp;

    private String targetName;

    private boolean wasWeak;

    //USER INFORMATION
    private Integer userPp;

    private String userName;

    //ABILTY INFORMATION
    private String abilityDescription;

    private String abilityName;

    private Integer damageCaused;

    private Integer valueRolled;

}
