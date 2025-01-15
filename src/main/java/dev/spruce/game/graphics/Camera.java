package dev.spruce.game.graphics;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.util.MathUtils;

import java.io.Serializable;

public class Camera implements Serializable {

    private float x, y;
    private float targetX, targetY;
    private boolean interpolateCamera;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
    }

    public void update(double delta) {
        this.x = interpolateCamera ? MathUtils.lerp(this.x, targetX, 0.1f) : targetX;
        this.y = interpolateCamera ? MathUtils.lerp(this.y, targetY, 0.1f) : targetY;
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void centerOn(float x, float y, boolean interpolated) {
        targetX = x - Window.getInstance().getWidth() / 2f;
        targetY = y - Window.getInstance().getHeight() / 2f;
        interpolateCamera = interpolated;
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
