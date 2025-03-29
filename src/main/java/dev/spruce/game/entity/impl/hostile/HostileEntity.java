package dev.spruce.game.entity.impl.hostile;

import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;

import java.awt.*;

public abstract class HostileEntity extends DamageableEntity {


    public HostileEntity(float x, float y, float width, float height, int health) {
        super(x, y, width, height, health);
    }
}
