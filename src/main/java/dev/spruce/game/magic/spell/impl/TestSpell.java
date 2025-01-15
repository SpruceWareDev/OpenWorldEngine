package dev.spruce.game.magic.spell.impl;

import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.entity.impl.projectile.RockPellet;
import dev.spruce.game.magic.spell.Spell;
import dev.spruce.game.state.impl.GameState;

public class TestSpell extends Spell {

    public TestSpell() {
        super("Test", 1);
    }

    @Override
    public void cast(GameState gameState, float x, float y, float angle) {
        float dx = (float) Math.cos(angle) * Projectile.BASE_SPEED;
        float dy = (float) Math.sin(angle) * Projectile.BASE_SPEED;
        gameState.getEntityManager().spawn(new RockPellet(gameState.getPlayer(), x, y, dx, dy, 16, 16));
    }
}
