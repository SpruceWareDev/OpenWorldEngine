package dev.spruce.game.entity.impl.hostile;

import dev.spruce.game.Game;
import dev.spruce.game.entity.impl.projectile.Fireball;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.util.MathUtils;

import java.awt.*;

public class TestEnemy extends HostileEntity {

    public TestEnemy(float x, float y, float width, float height) {
        super(x, y, width, height, 30);
    }

    @Override
    public void update(double delta) {
        if (MathUtils.isWithinDistance(this, Game.getStateManager().getGameState().getPlayer(), 20f)) {
            Game.getStateManager().getGameState().getEntityManager().spawn(new Fireball(this, getX(), getY(), 1, 1));
        }
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.red);
        graphics.fillRect(
                (int) (getX() - camera.getX()),
                (int) (getY() - camera.getY()),
                (int) getWidth(), (int) getHeight()
        );
    }

    @Override
    public void onDeath() {
        Game.getStateManager().getGameState().getEntityManager().despawn(this);
    }
}
