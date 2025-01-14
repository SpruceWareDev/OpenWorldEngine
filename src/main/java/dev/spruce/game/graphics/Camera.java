package dev.spruce.game.graphics;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.util.MathUtils;

import java.io.Serializable;

public class Camera implements Serializable {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void centerOn(float x, float y, boolean interpolated) {
        float newX = x - Window.getInstance().getWidth() / 2f;
        float newY = y - Window.getInstance().getHeight() / 2f;
        this.x = interpolated ? MathUtils.lerp(this.x, newX, 0.001f) : newX;
        this.y = interpolated ? MathUtils.lerp(this.y, newY, 0.001f) : newY;
    }

    public void centerOn(Entity entity, boolean interpolated) {
        centerOn(entity.getX() + (entity.getWidth() / 2), entity.getY() + (entity.getHeight() / 2), interpolated);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
