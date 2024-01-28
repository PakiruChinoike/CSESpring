package com.io.github.pakiruchinoike.domain.entity.character;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attributes")
public class Attributes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attributes_id")
    private Integer id;

    @Column(name = "charisma")
    @NotNull(message = "{attribute.value.empty}")
    private Integer charisma;

    @Column(name = "constitution")
    @NotNull(message = "{attribute.value.empty}")
    private Integer constitution;

    @Column(name = "dexterity")
    @NotNull(message = "{attribute.value.empty}")
    private Integer dexterity;

    @Column(name = "intelligence")
    @NotNull(message = "{attribute.value.empty}")
    private Integer intelligence;

    @Column(name = "strenght")
    @NotNull(message = "{attribute.value.empty}")
    private Integer strenght;

    @Column(name = "wisdom")
    @NotNull(message = "{attribute.value.empty}")
    private Integer wisdom;
    
}
