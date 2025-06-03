package dev.spruce.game.entity.impl.projectile;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.graphics.particle.ParticleRenderer;
import dev.spruce.game.state.StateManager;
import dev.spruce.game.util.GameUtils;

import java.awt.*;

public class Fireball extends Projectile {

    public Fireball(Entity owner, float x, float y, float dx, float dy) {
        super(owner, x, y, dx, dy, 10, 10, 10, 60);
        setOnFire(true);
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));

        for (int i = 0; i < 2; i++) {
            GameUtils.spawnParticle(new Particle(getX(), getY(), 5f, 50, Particle.ParticleType.SQUARE, Color.ORANGE));

        }
    }

    @Override
    public void render(Camera camera) {
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        Raylib.DrawCircle(x, y, (int) getWidth(), Colors.RED);
    }
}
