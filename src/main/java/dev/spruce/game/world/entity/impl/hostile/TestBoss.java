package dev.spruce.game.world.entity.impl.hostile;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.graphics.Camera;

public class TestBoss extends Boss {

    public TestBoss(float x, float y, float width, float height) {
        super(x, y, width, height, 1000);
    }

    @Override
    public void onDeath() {
        Game.getStateManager().getGameState().ifPresent(gameState -> {
            gameState.getEntityManager().despawn(this);
        });
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Camera camera) {
        Raylib.DrawRectangle(
            (int) (getX() - camera.getX()),
            (int) (getY() - camera.getY()),
            (int) getWidth(),
            (int) getHeight(),
                Colors.GOLD
        );
    }
}
