package dev.spruce.game.magic.spell;

import dev.spruce.game.Game;
import dev.spruce.game.entity.impl.projectile.Fireball;
import dev.spruce.game.magic.ManaManager;
import dev.spruce.game.magic.spell.impl.BasicFireSpell;
import dev.spruce.game.magic.spell.impl.TestSpell;
import dev.spruce.game.state.impl.GameState;

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

    public void castCurrentSpell(ManaManager manaManager, float x, float y, float angle) {
        GameState gs = Game.getStateManager().getGameState();
        if (manaManager.removeMana(currentSpell.getManaCost())) {
            currentSpell.cast(gs, x, y, angle);
        }
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
