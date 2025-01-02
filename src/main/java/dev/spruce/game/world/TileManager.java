package dev.spruce.game.world;

import dev.spruce.game.assets.Textures;

public class TileManager {

    private static TileManager instance;

    public final Tile GRASS = new Tile(0, Tile.SIZE, false, Textures.GRASS);
    public final Tile CRACKED_STONE = new Tile(1, Tile.SIZE, false, Textures.CRACKED_STONE);

    public static TileManager getInstance() {
        if (instance == null) {
            instance = new TileManager();
        }
        return instance;
    }
}
