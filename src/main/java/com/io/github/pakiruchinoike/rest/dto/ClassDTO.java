package com.io.github.pakiruchinoike.rest.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.io.github.pakiruchinoike.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
 
    @NotEmpty(message = "{name.empty}")
    private String name;

    @NotNull(message = "{class.value.empty}")
    private Integer fortune;

    @NotNull(message = "{class.value.empty}")
    private Integer hp;

    @NotNull(message = "{class.value.empty}")
    private Integer pp;

    @NotNull(message = "{class.value.empty}")
    private Integer attributes;

    @NotEmptyList(message = "{list.empty}")
    private List<AbilityDTO> classAbilities;
}
