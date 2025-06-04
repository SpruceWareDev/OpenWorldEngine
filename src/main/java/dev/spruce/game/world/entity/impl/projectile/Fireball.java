package dev.spruce.game.world.entity.impl.projectile;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.util.GameUtils;
import dev.spruce.game.util.TimerUtils;
import dev.spruce.game.world.entity.Entity;

import java.awt.*;

public class Fireball extends Projectile {

    public Fireball(Entity owner, float x, float y, float dx, float dy) {
        super(owner, x, y, dx, dy, 10, 10, 10, TimerUtils.ticksFromSeconds(2f));
        setOnFire(true);
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));

        GameUtils.spawnParticle(new Particle(getX(), getY(), 5f, getLifeTimeTicks() / 4, Particle.ParticleType.SQUARE, Color.ORANGE));
    }

    @Override
    public void render(Camera camera) {
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        Raylib.DrawCircle(x, y, (int) getWidth(), Colors.RED);
    }
}
