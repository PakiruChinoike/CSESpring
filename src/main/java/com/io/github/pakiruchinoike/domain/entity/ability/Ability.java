package com.io.github.pakiruchinoike.domain.entity.ability;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.pakiruchinoike.domain.entity.character.Class;
import com.io.github.pakiruchinoike.domain.enums.CheckType;
import com.io.github.pakiruchinoike.domain.enums.Damagetype;
import com.io.github.pakiruchinoike.domain.enums.TargetType;
import com.io.github.pakiruchinoike.domain.enums.UsedAttribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ability")
public class Ability {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ability_id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "{name.empty}")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "damage_type")
    @NotNull(message = "{ability.value.empty}")
    private Damagetype damageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    @NotNull(message = "{ability.value.empty}")
    private TargetType targetType;

    @Enumerated(EnumType.STRING)
    @Column(name = "check_type")
    @NotNull(message = "{ability.value.empty}")
    private CheckType checkType;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_attribute")
    @NotNull(message = "{ability.value.empty}")
    private UsedAttribute usedAttribute;

    @Column(name = "description")
    @NotNull(message = "{ability.value.empty}")
    private String description;

    @Column(name = "required_level")
    @NotNull(message = "{ability.value.empty}")
    private Integer level;

    @Column(name = "difficulty_class")
    @NotNull(message = "{ability.value.empty}")
    private Integer difficulty;

    @Column(name = "power_cost")
    @NotNull(message = "{ability.value.empty}")
    private Integer cost;

    @Column(name = "damage_amount")
    @NotNull(message = "{ability.value.empty}")
    private Integer damageAmount;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonIgnore
    private Class classes;

    //CLASS METHODS

    public String useAbility() {
        return null;
    }
}
