package com.io.github.pakiruchinoike.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.io.github.pakiruchinoike.domain.enums.CheckType;
import com.io.github.pakiruchinoike.domain.enums.Damagetype;
import com.io.github.pakiruchinoike.domain.enums.TargetType;
import com.io.github.pakiruchinoike.domain.enums.UsedAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityDTO {
    
    @NotEmpty(message = "{ability.value.empty}")
    private String name;

    @NotNull(message = "{ability.value.empty}")
    private Damagetype damageType;

    @NotNull(message = "{ability.value.empty}")
    private TargetType targetType;

    @NotNull(message = "{ability.value.empty}")
    private CheckType checkType;

    @NotNull(message = "{ability.value.empty}")
    private UsedAttribute usedAttribute;

    @NotEmpty(message = "{ability.value.empty}")
    private String description;

    @NotNull(message = "{ability.value.empty}")
    private Integer level;

    @NotNull(message = "{ability.value.empty}")
    private Integer difficulty;

    @NotNull(message = "{ability.value.empty}")
    private Integer cost;

    @NotNull(message = "{ability.value.empty}")
    private Integer damageAmount;
}
