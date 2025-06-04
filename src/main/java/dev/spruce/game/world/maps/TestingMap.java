package dev.spruce.game.world.maps;

import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.Tile;
import dev.spruce.game.world.TileManager;
import dev.spruce.game.world.entity.impl.environment.StagePortal;

public class TestingMap extends Map {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public TestingMap() {
        super(WIDTH, HEIGHT, 0);
    }

    @Override
    public void generate(GameState gameState) {
        fillMap(TileManager.getInstance().GRASS);
        gameState.getEntityManager().spawn(
                new StagePortal(
                        getSpawnX() + Tile.SIZE * 2, getSpawnY() + Tile.SIZE * 2
                )
        );
    }

    @Override
    public float getSpawnX() {
        return (WIDTH * Tile.SIZE) / 2f;
    }

    @Override
    public float getSpawnY() {
        return (HEIGHT * Tile.SIZE) / 2f;
    }
}
