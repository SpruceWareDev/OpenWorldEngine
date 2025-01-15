package dev.spruce.game.entity.impl.projectile;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;

import java.awt.*;

public class Fireball extends Projectile {

    public Fireball(Entity owner, float x, float y, float dx, float dy) {
        super(owner, x, y, dx, dy, 10, 10, 10, 60);
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        graphics.fillOval((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }
}
