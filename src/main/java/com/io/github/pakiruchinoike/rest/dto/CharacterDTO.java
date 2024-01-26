package com.io.github.pakiruchinoike.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {
    
    @NotEmpty(message = "{name.empty}")
    private String name;

    @NotNull(message = "{character.value.empty}")
    private Integer level;

    @NotNull(message = "{character.value.empty}")
    private Integer hp;

    @NotNull(message = "{character.value.empty}")
    private Integer pp;

    @NotNull(message = "{character.value.empty}")
    private Integer ap;

    @NotNull(message = "{character.value.empty}")
    private Integer fortune;

    @NotNull(message = "{character.value.empty}")
    private Integer charClass;

    @NotNull(message = "{character.value.empty}")
    private Integer attributes;

}
