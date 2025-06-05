package dev.spruce.game.world.entity;

import dev.spruce.game.Game;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.TimerUtils;
import dev.spruce.game.world.entity.impl.hostile.HostileEntity;

public abstract class DamageableEntity extends Entity {

    private int health;
    private int maxHealth;

    public static final int PASSIVE_DAMAGE_TICKS = TimerUtils.ticksFromSeconds(1);
    private int passiveDamageTickTimer = 0;

    public DamageableEntity(float x, float y, float width, float height, int health) {
        super(x, y, width, height);
        this.health = health;
        this.maxHealth = health;
    }

    public void dealDamage(int amount) {
        if (health - amount <= 0) {
            this.health = 0;
            if (this instanceof HostileEntity) {
                Game.getStateManager().getGameState().ifPresent(GameState::addKill);
            }
            onDeath();
            return;
        }
        health -= amount;
    }

    public void handlePassiveDamage() {
        if (isOnFire() && passiveDamageTickTimer >= PASSIVE_DAMAGE_TICKS) {
            dealDamage(1);
            passiveDamageTickTimer = 0;
        }
        passiveDamageTickTimer++;
    }

    public abstract void onDeath();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
