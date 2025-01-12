package dev.spruce.game.entity.impl.projectile;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.state.impl.GameState;

public abstract class Projectile extends Entity {

    public static final float BASE_SPEED = 12f;

    private Entity owner;
    private int damage;
    private int lifeTimeTicks;

    private int ticksAlive = 0;

    public Projectile(Entity owner, float x, float y, float dx, float dy, float width, float height, int damage, int lifeTimeTicks) {
        super(x, y, width, height);
        this.owner = owner;
        this.damage = damage;
        this.lifeTimeTicks = lifeTimeTicks;
        setDx(dx);
        setDy(dy);
    }

    protected void handleLifetime() {
        ticksAlive++;
        if (ticksAlive >= lifeTimeTicks) {
            GameState.getEntityManager().despawn(this);
        }
    }

    public Entity getOwner() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }

    public int getLifeTimeTicks() {
        return lifeTimeTicks;
    }
}
