package dev.spruce.game.entity;

import dev.spruce.game.Game;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;
import dev.spruce.game.util.MathUtils;

import java.awt.*;
import java.io.Serializable;

public abstract class Entity implements Serializable {
    // World position
    private float x;
    private float y;

    // Size
    private float width;
    private float height;

    // Velocity
    private float dx;
    private float dy;

    private EntityCollider entityCollider;
    protected boolean shouldCollide = false;

    private boolean onFire = false;

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = 0;
        this.dy = 0;
        this.entityCollider = new EntityCollider(this, new Rectangle(0, 0, (int) width, (int) height));
    }

    public abstract void update(double delta);

    public abstract void render(Graphics graphics, Camera camera);

    public void updateParticles() {
        if (onFire) {
            int x = (int) (getX() + MathUtils.randomFloat(0, getEntityCollider().getBounds().width));
            int y = (int) (getY() + MathUtils.randomFloat(0, getEntityCollider().getBounds().height));
            Game.getStateManager()
                    .getGameState()
                    .getParticleRenderer()
                    .spawnParticle(x, y, 5f, 10, Particle.ParticleType.SQUARE, Color.ORANGE);
        }
    }

    protected void renderBoundingBox(Graphics graphics, Camera camera) {
        int x = (int) (getX() + getEntityCollider().getBounds().x - camera.getX());
        int y = (int) (getY() + getEntityCollider().getBounds().y - camera.getY());
        int width = getEntityCollider().getBounds().width;
        int height = getEntityCollider().getBounds().height;
        graphics.setColor(Color.blue);
        graphics.drawRect(x, y, width, height);

        // draw health of entity
        if (this instanceof DamageableEntity damageable) {
            int healthBarWidth = (int) ((damageable.getHealth() / (float) damageable.getMaxHealth()) * width);
            graphics.setColor(Color.RED);
            graphics.fillRect(x, y - 5, healthBarWidth, 3);
        }
    }

    public void resetVelocity() {
        dx = 0;
        dy = 0;
    }

    /**
    Method that can be called by children of entity class to apply
    the current dx and dy velocity values to the entity's current
    position.
     @param delta delta time for updates (from update method)
     @param speed the speed the entity should move at
     */
    protected void applyVelocity(double delta, float speed, boolean collidingX, boolean collidingY) {
        if(!collidingX) setX((float) (getX() + (getDx() * delta * speed)));
        if(!collidingY) setY((float) (getY() + (getDy() * delta * speed)));
    }

    public void enableCollision() {
        shouldCollide = true;
    }

    public boolean hasCollisions() {
        return shouldCollide;
    }

    // Finds the x position of the entity in window coordinates
    public float getScreenX(Camera camera) {
        return x - camera.getX();
    }

    // Finds the y position of the entity in window coordinates
    public float getScreenY(Camera camera) {
        return y - camera.getY();
    }

    public boolean isEntityOnScreen(Camera camera) {
        return getScreenX(camera) + width > 0 &&
                getScreenX(camera) < Window.getInstance().getWidth() &&
                getScreenY(camera) + height > 0 &&
                getScreenY(camera) < Window.getInstance().getHeight();
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

    public boolean isOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }
}
