package dev.spruce.game.assets.managers;

import com.raylib.Raylib;
import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.util.ImageUtils;
import dev.spruce.game.world.TileManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileTextureManager extends AssetManager<Integer, Raylib.Texture> {

    private final HashMap<Integer, Raylib.Texture> tiles;

    public TileTextureManager() {
        tiles = new HashMap<>();
        tiles.put(TileManager.getInstance().GRASS.getId(), Raylib.LoadTexture("assets/textures/tile_grass.png"));
        tiles.put(TileManager.getInstance().CRACKED_STONE.getId(), Raylib.LoadTexture("assets/textures/tile_cracked_stone.png"));
        tiles.put(TileManager.getInstance().WATER.getId(), Raylib.LoadTexture("assets/textures/tile_water.png"));
    }

    @Override
    public Raylib.Texture getAsset(Integer assetId) {
        return tiles.get(assetId);
    }

    @Override
    public void dispose() {
        for (Raylib.Texture texture : tiles.values()) {
            Raylib.UnloadTexture(texture);
        }
    }
}
