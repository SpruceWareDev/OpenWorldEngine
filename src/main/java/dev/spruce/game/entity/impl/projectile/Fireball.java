package dev.spruce.game.entity.impl.projectile;

import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.graphics.particle.ParticleRenderer;
import dev.spruce.game.state.StateManager;

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
        Game.getStateManager()
                .getGameState()
                .getParticleRenderer()
                .spawnParticle(getX(), getY(), 5, 20, Particle.ParticleType.SQUARE, Color.ORANGE);
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        graphics.fillOval((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }
}
