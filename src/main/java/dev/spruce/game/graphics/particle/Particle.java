package dev.spruce.game.graphics.particle;

import dev.spruce.game.graphics.Camera;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;

public class Particle {

    private float x, y;
    private float size;
    private Color color;
    private int lifetimeTicks;

    private int lifetimeTracker;

    public Particle(float x, float y, float size, int lifetimeTicks, ParticleType type, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.lifetimeTicks = lifetimeTicks;
    }

    public void update(double delta) {
        updateLifetime();
        float xVel = (float) (Math.random() * 2 - 1);
        float yVel = (float) (Math.random() * 2 - 1);
        x += xVel;
        y += yVel;
    }

    public void render(Graphics graphics, Camera camera) {
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.min(255 - (int) (255 * ((float) lifetimeTracker / lifetimeTicks)), 255));

        switch (ParticleType.CIRCLE) {
            case CIRCLE -> RenderUtils.drawOval(graphics, x - camera.getX(), y - camera.getY(), size, size, color);
            case SQUARE -> RenderUtils.drawRect(graphics, x - camera.getX(), y - camera.getY(), size, size, color);
            case TRIANGLE -> RenderUtils.drawTriangle(graphics, x - camera.getX(), y - camera.getY(), size, size, color);
        }
    }

    protected void updateLifetime() {
        lifetimeTracker++;
    }

    public boolean isDead() {
        return lifetimeTracker >= lifetimeTicks;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getLifetimeTicks() {
        return lifetimeTicks;
    }

    public enum ParticleType {
        CIRCLE,
        SQUARE,
        TRIANGLE;
    }
}
