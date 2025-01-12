package dev.spruce.game.entity;

import dev.spruce.game.state.impl.GameState;

public abstract class DamageableEntity extends Entity {

    private int health;
    private int maxHealth;

    public DamageableEntity(GameState gameState, float x, float y, float width, float height, int health) {
        super(gameState, x, y, width, height);
        this.health = health;
        this.maxHealth = health;
    }

    public void dealDamage(int amount) {
        if (health - amount <= 0) {
            onDeath();
            return;
        }
        health -= amount;
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
