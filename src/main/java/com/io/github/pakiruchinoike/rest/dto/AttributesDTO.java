package com.io.github.pakiruchinoike.rest.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributesDTO {

    @NotNull(message = "{attribute.value.empty}")
    private Integer charisma;

    @NotNull(message = "{attribute.value.empty}")
    private Integer constitution;

    @NotNull(message = "{attribute.value.empty}")
    private Integer dexterity;

    @NotNull(message = "{attribute.value.empty}")
    private Integer intelligence;

    @NotNull(message = "{attribute.value.empty}")
    private Integer strenght;

    @NotNull(message = "{attribute.value.empty}")
    private Integer wisdom;

}
