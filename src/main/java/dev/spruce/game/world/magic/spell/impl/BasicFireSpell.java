package dev.spruce.game.world.magic.spell.impl;

import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.entity.impl.projectile.Fireball;
import dev.spruce.game.world.entity.impl.projectile.Projectile;
import dev.spruce.game.world.magic.spell.Spell;

public class BasicFireSpell extends Spell {

    public BasicFireSpell() {
        super("Basic Fire", 5);
    }

    @Override
    public void cast(GameState gameState, float x, float y, float angle) {
        SoundManager.getInstance().playSound("fireball", 50);
        float dx = (float) Math.cos(angle) * Projectile.BASE_SPEED;
        float dy = (float) Math.sin(angle) * Projectile.BASE_SPEED;
        gameState.getEntityManager().spawn(new Fireball(gameState.getPlayer(), x, y, dx, dy));
    }
}