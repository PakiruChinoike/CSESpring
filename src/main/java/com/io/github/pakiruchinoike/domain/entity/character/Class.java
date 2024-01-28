package com.io.github.pakiruchinoike.domain.entity.character;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.io.github.pakiruchinoike.domain.entity.ability.Ability;
import com.io.github.pakiruchinoike.domain.enums.Classname;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "class")
public class Class {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "classname")
    private Classname classname;

    @Column(name = "name")
    @NotEmpty(message = "{name.empty}")
    private String name;

    @Column(name = "base_fortune")
    @NotNull(message = "{class.value.empty}")
    private Integer fortune;

    @Column(name = "base_hp")
    @NotNull(message = "{class.value.empty}")
    private Integer hp;

    @Column(name = "base_pp")
    @NotNull(message = "{class.value.empty}")
    private Integer pp;

    @OneToOne
    @JoinColumn(name = "base_attributes")
    private Attributes attributes;

    @OneToMany(mappedBy = "classes")
    private List<Ability> abilities;
}

