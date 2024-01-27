package com.io.github.pakiruchinoike.rest.dto;

import com.io.github.pakiruchinoike.domain.enums.Classname;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharCreationDTO {
    
    private String name;
    
    private Classname classname;

    private AttributesDTO attributes;

}
