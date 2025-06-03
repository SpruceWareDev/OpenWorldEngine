package dev.spruce.game.entity.impl.projectile;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;

public class RockPellet extends Projectile {

    public RockPellet(Entity owner, float x, float y, float dx, float dy, float width, float height) {
        super(owner, x, y, dx, dy, width, height, 2, 60);
    }

    @Override
    public void update(double delta) {
        handleLifetime();
        setX(getX() + (getDx() * (float) delta));
        setY(getY() + (getDy() * (float) delta));
    }

    @Override
    public void render(Camera camera) {
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        Raylib.DrawRectangle(x, y, (int) getWidth(), (int) getHeight(), Colors.GRAY);
    }
}
