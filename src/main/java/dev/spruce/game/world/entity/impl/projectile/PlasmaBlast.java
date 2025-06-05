package dev.spruce.game.world.entity.impl.projectile;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.Colours;
import dev.spruce.game.graphics.particle.Particle;
import dev.spruce.game.util.GameUtils;
import dev.spruce.game.util.TimerUtils;
import dev.spruce.game.world.entity.Entity;

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
            GameUtils.spawnParticle(new Particle(getX(), getY(), 8f, 100,
                    Particle.ParticleType.SQUARE, Colours.BLUE));
        }
    }

    @Override
    public void render(Camera camera) {
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        Raylib.DrawRectangle(x, y, (int) getWidth(), (int) getHeight(), Colors.PURPLE);
    }
}
