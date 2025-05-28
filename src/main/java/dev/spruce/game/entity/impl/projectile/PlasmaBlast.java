package dev.spruce.game.entity.impl.projectile;

import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.util.TimerUtils;

import java.awt.*;

public class PlasmaBlast extends Projectile {

    public PlasmaBlast(Entity owner, float x, float y, float dx, float dy) {
        super(owner, x, y, dx, dy, 12, 12, 20, TimerUtils.ticksFromSeconds(0.5f));
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));

        for (int i = 0; i < 2; i++) {
            Game.getStateManager()
                    .getGameState()
                    .getParticleRenderer()
                    .spawnParticle(
                            getX(), getY(), 8f, 100,
                            Particle.ParticleType.SQUARE, Color.BLUE
                    );

        }
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(new Color(0x6B20DB));
        graphics.fillOval((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }
}
