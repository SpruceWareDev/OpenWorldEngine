package dev.spruce.game.world.entity.impl.environment;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.Tile;

public class StagePortal extends Portal {

    private final Raylib.Color color = Colors.RED;

    public StagePortal(float x, float y) {
        super(x, y, 64, 128);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Camera camera) {
        Raylib.DrawEllipse(
            (int) (getX() - camera.getX() + (float) Tile.SIZE / 2),
            (int) (getY() - camera.getY() + (float) Tile.SIZE / 2),
            getWidth() / 2, getHeight() / 2, color
        );
    }

    @Override
    public void interact() {
        System.out.println("Interacted with Stage Portal at (" + getX() + ", " + getY() + ")");
        Game.getStateManager().getGameState().ifPresent(GameState::activateStageTransition);
    }

    @Override
    public float radius() {
        return Tile.SIZE * 2;
    }
}
