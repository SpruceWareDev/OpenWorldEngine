package dev.spruce.game.world.entity.impl.hostile;

public abstract class Boss extends HostileEntity {

    public Boss(float x, float y, float width, float height, int health) {
        super(x, y, width, height, health, 1000000);
    }
}
