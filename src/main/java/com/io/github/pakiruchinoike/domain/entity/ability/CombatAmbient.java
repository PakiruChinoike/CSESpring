package com.io.github.pakiruchinoike.domain.entity.ability;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.io.github.pakiruchinoike.domain.entity.character.Character;
import com.io.github.pakiruchinoike.domain.entity.creature.Ally;
import com.io.github.pakiruchinoike.domain.entity.creature.Creature;
import com.io.github.pakiruchinoike.domain.entity.creature.Enemy;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "combat_ambient")
public final class CombatAmbient {
    
    @Column(name = "round_value")
    private static Integer round;

    @Column(name = "turn_value")
    private static Integer turn;

    @Column(name = "turn_per_round")
    private Integer turnPerRound;

    @OneToOne
    @JoinColumn(name = "character")
    private static Character character;

    @OneToMany(mappedBy = "creature")
    private static List<Creature> allies;

    @OneToMany(mappedBy = "creature")
    private static List<Creature> enemies;

    public CombatAmbient() {
        round = 0;
        turn = 0;

        turnPerRound = 0;

        character = new Character();
        allies = new ArrayList<Creature>();
        enemies = new ArrayList<Creature>();
    }

    public CombatAmbient(Character character, List<Creature> allies, List<Creature> enemies) {
        round = 0;
        turn = 0;

        this.character = character;
        this.allies = allies;
        this.enemies = enemies;

        this.turnPerRound = allies.size()+1+enemies.size();
    }

    public Integer getRound() {
        return round;
    }

    public Integer getTurn() {
        return turn;
    }

    public Integer passTurn() {
        if(turn>=turnPerRound) {
            CombatAmbient.turn = 0;
            return passRound();
        } else {
            CombatAmbient.turn = turn + 1;
            return turn;
        }
    }

    public Integer passRound() {
        CombatAmbient.round = round +1;
        return round;
    }

    public void resetCombat() {
        CombatAmbient.round = 0;
        CombatAmbient.turn = 0;

        CombatAmbient.character = new Character();
        CombatAmbient.allies = new ArrayList<Creature>();
        CombatAmbient.enemies = new ArrayList<Creature>();
    }

}
