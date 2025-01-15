package dev.spruce.game.magic.spell;

import dev.spruce.game.state.impl.GameState;

import java.io.Serializable;

public abstract class Spell implements Serializable {

    private final String name;
    private final int manaCost;

    public Spell(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    public abstract void cast(GameState gameState, float x, float y, float angle);

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }
}
