package dev.spruce.game.entity.impl.projectile;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class RockPellet extends Projectile {

    public RockPellet(GameState gameState, Entity owner, float x, float y, float dx, float dy, float width, float height) {
        super(gameState, owner, x, y, dx, dy, width, height, 2, 60);
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }
}
