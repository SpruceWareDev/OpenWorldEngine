package dev.spruce.game.assets.managers;

import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.util.ImageUtils;
import dev.spruce.game.world.TileManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileTextureManager extends AssetManager<Integer, BufferedImage> {

    private final HashMap<Integer, BufferedImage> tiles;

    public TileTextureManager() {
        tiles = new HashMap<>();
        tiles.put(TileManager.getInstance().GRASS.getId(), ImageUtils.loadImage("assets/textures/tile_grass.png"));
        tiles.put(TileManager.getInstance().CRACKED_STONE.getId(), ImageUtils.loadImage("assets/textures/tile_cracked_stone.png"));
        tiles.put(TileManager.getInstance().WATER.getId(), ImageUtils.loadImage("assets/textures/tile_water.png"));
    }

    @Override
    public BufferedImage getAsset(Integer assetId) {
        return tiles.get(assetId);
    }
}
