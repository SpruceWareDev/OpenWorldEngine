package dev.spruce.game.graphics;

import dev.spruce.game.entity.Entity;

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

    public void centerOn(float x, float y) {
        this.x = x - Window.getInstance().getWidth() / 2;
        this.y = y - Window.getInstance().getHeight() / 2;
    }

    public void centerOn(Entity entity) {
        centerOn(entity.getX() + (entity.getWidth() / 2), entity.getY() + (entity.getHeight() / 2));
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
