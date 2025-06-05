package dev.spruce.game.world.magic.spell.impl;

import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.entity.impl.projectile.PlasmaBlast;
import dev.spruce.game.world.entity.impl.projectile.Projectile;
import dev.spruce.game.world.magic.spell.Spell;

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
