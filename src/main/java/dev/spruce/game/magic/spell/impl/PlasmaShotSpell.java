package dev.spruce.game.magic.spell.impl;

import dev.spruce.game.entity.impl.projectile.PlasmaBlast;
import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.magic.spell.Spell;
import dev.spruce.game.state.impl.GameState;

public class PlasmaShotSpell extends Spell {

    public PlasmaShotSpell() {
        super("Plasma Shot", 10);
    }

    @Override
    public void cast(GameState gameState, float x, float y, float angle) {
        float dx = (float) Math.cos(angle) * Projectile.BASE_SPEED;
        float dy = (float) Math.sin(angle) * Projectile.BASE_SPEED;
        gameState.getEntityManager().spawn(new PlasmaBlast(gameState.getPlayer(), x, y, dx, dy));
    }
}
