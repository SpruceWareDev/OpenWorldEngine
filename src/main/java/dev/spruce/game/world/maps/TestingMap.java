package dev.spruce.game.world.maps;

import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.Tile;
import dev.spruce.game.world.TileManager;

public class TestingMap extends Map {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public TestingMap() {
        super(WIDTH, HEIGHT, 0);
    }

    @Override
    public void generate(GameState gameState) {
        fillMap(TileManager.getInstance().GRASS);
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
