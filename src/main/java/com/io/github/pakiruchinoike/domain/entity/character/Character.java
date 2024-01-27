package com.io.github.pakiruchinoike.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.pakiruchinoike.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "character")
public class Character {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "{name.empty}")
    private String name;

    @Column(name = "level")
    @NotNull(message = "{character.value.empty}")
    private Integer level;

    @Column(name = "health_points")
    @NotNull(message = "{character.value.empty}")
    private Integer hp;

    @Column(name = "power_points")
    @NotNull(message = "{character.value.empty}")
    private Integer pp;

    @Column(name = "armor_points")
    @NotNull(message = "{character.value.empty}")
    private Integer ap;

    @Column(name = "fortune")
    @NotNull(message = "{character.value.empty}")
    private Integer fortune;

    @OneToMany(mappedBy = "character")
    @NotEmptyList(message = "{list.empty}")
    private List<Weakness> weaknessess;

    @OneToOne
    @JoinColumn(name = "attributes")
    private Attributes attributes;

    @OneToOne
    @JoinColumn(name = "class")
    @JsonIgnore
    private Class charClass;

    @ManyToOne
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;

}
