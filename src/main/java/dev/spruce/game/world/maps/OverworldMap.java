package dev.spruce.game.world.maps;

import dev.spruce.game.entity.impl.environment.AcaciaTree;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.Noise;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.Tile;
import dev.spruce.game.world.TileManager;

public class OverworldMap extends Map {

    private int spawnX, spawnY;
    private Noise noise;

    public OverworldMap(GameState gameState, int width, int height) {
        super(gameState, width, height);
    }

    @Override
    public void generate() {
        noise = new Noise(4, 2f, 2f, 69420);
        float[][] waterNoiseMap = noise.generateNoise(width, height);

        // Fill map with grass
        fillMap(TileManager.getInstance().GRASS);
        // Generate spawn point
        generateSpawn();
        // Generate water
        generateWater(waterNoiseMap);
        // Generate foliage
        generateFoliage();
    }

    private void fillMap(Tile tile) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = tile;
            }
        }
    }

    private void generateSpawn() {
        // Generate a spawn point not close to edges of world
        spawnX = (int) (Math.random() * (width - (width / 3))) + width / 3;
        spawnY = (int) (Math.random() * (height - (height / 3))) + height / 3;

        // Spawn a 3x3 cracked stone area around spawn point
        for (int x = spawnX - 1; x <= spawnX + 1; x++) {
            for (int y = spawnY - 1; y <= spawnY + 1; y++) {
                tiles[x][y] = TileManager.getInstance().CRACKED_STONE;
            }
        }
    }

    private void generateWater(float[][] noiseMap) {
        // Generate water
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (noiseMap[x][y] > 0.8) {
                    tiles[x][y] = TileManager.getInstance().WATER;
                }
            }
        }
    }

    private void generateFoliage() {
        // Generate foliage
        for (int i = 0; i < ((width * height) / 8); i++) {
            int x = (int) (Math.random() * width) * Tile.SIZE;
            int y = (int) (Math.random() * height) * Tile.SIZE;
            if (tiles[x / Tile.SIZE][y / Tile.SIZE] == TileManager.getInstance().GRASS) {
                gameState.getEntityManager().spawn(new AcaciaTree(gameState, x, y));
            }
        }
    }

    public int getSpawnX() {
        return spawnX * Tile.SIZE;
    }

    public int getSpawnY() {
        return spawnY * Tile.SIZE;
    }
}
