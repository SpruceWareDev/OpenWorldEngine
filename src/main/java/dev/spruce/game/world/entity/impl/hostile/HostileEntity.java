package dev.spruce.game.world.entity.impl.hostile;

import dev.spruce.game.world.entity.DamageableEntity;

public abstract class HostileEntity extends DamageableEntity {

    private final int spawnCost;

    public HostileEntity(float x, float y, float width, float height, int health, int spawnCost) {
        super(x, y, width, height, health);
        this.spawnCost = spawnCost;
    }

    public int getSpawnCost() {
        return spawnCost;
    }
}
