package dev.spruce.game.world.entity.impl.hostile;

import dev.spruce.game.world.entity.DamageableEntity;

public abstract class HostileEntity extends DamageableEntity {

    public HostileEntity(float x, float y, float width, float height, int health) {
        super(x, y, width, height, health);
    }
}
