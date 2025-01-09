package dev.spruce.game.entity;

import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;

import java.awt.*;
import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected GameState gameState;

    // World position
    private float x;
    private float y;

    // Size
    private float width;
    private float height;

    // Velocity
    private float dx;
    private float dy;

    private int health;
    private int maxHealth;

    private EntityCollider entityCollider;
    protected boolean shouldCollide = false;

    public Entity(GameState gameState, float x, float y, float width, float height, int health) {
        this.gameState = gameState;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.maxHealth = health;
        this.dx = 0;
        this.dy = 0;
        this.entityCollider = new EntityCollider(this, new Rectangle(0, 0, (int) width, (int) height));
    }

    public abstract void update(double delta);

    public abstract void render(Graphics graphics, Camera camera);

    protected void renderBoundingBox(Graphics graphics, Camera camera) {
        int x = (int) (getX() + getEntityCollider().getBounds().x - camera.getX());
        int y = (int) (getY() + getEntityCollider().getBounds().y - camera.getY());
        int width = getEntityCollider().getBounds().width;
        int height = getEntityCollider().getBounds().height;
        graphics.setColor(Color.blue);
        graphics.drawRect(x, y, width, height);
    }

    public void resetVelocity() {
        dx = 0;
        dy = 0;
    }

    public void enableCollision() {
        shouldCollide = true;
    }

    public boolean hasCollisions() {
        return shouldCollide;
    }

    // Finds the x position of the entity in window coordinates
    public float getScreenX() {
        return x - gameState.getCamera().getX();
    }

    // Finds the y position of the entity in window coordinates
    public float getScreenY() {
        return y - gameState.getCamera().getY();
    }

    public boolean isEntityOnScreen() {
        return getScreenX() + width > 0 && getScreenX() < Window.getInstance().getWidth() && getScreenY() + height > 0 && getScreenY() < Window.getInstance().getHeight();
    }

    public EntityCollider getEntityCollider() {
        return entityCollider;
    }

    public void setEntityCollider(EntityCollider entityCollider) {
        this.entityCollider = entityCollider;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

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
