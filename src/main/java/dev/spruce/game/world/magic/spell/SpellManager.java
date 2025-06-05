package dev.spruce.game.world.magic.spell;

import dev.spruce.game.Game;
import dev.spruce.game.world.magic.ManaManager;
import dev.spruce.game.world.magic.spell.impl.BasicFireSpell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpellManager implements Serializable {

    private List<Spell> spells;
    private Spell currentSpell;

    public SpellManager() {
        this.spells = new ArrayList<>();

        BasicFireSpell startingSpell = new BasicFireSpell();
        addSpell(startingSpell);
        currentSpell = startingSpell;
    }

    public void swapSpell(Spell spell) {
        if (!spells.contains(spell))
            throw new IllegalArgumentException("Spell not found in the spell list.");
        this.currentSpell = spell;
    }

    public void castCurrentSpell(ManaManager manaManager, float x, float y, float angle) {
        Game.getStateManager().getGameState().ifPresent(gameState -> {
            if (manaManager.removeMana(currentSpell.getManaCost())) {
                currentSpell.cast(gameState, x, y, angle);
            }
        });
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    public Spell getCurrentSpell() {
        return currentSpell;
    }

    public List<Spell> getSpells() {
        return spells;
    }
}
