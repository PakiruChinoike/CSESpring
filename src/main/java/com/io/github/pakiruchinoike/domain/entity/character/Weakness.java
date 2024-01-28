package com.io.github.pakiruchinoike.domain.entity.character;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.pakiruchinoike.domain.enums.Damagetype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weakness")
public class Weakness {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weakness_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "weakness")
    private Damagetype weakness;

    @ManyToOne
    @JoinColumn(name = "character_id")
    @JsonIgnore
    private Character character;

}
