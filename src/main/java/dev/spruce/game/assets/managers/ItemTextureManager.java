package dev.spruce.game.assets.managers;

import com.raylib.Raylib;
import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ItemTextureManager extends AssetManager<String, Raylib.Texture> {

    private final HashMap<String, Raylib.Texture> itemTextures;

    public ItemTextureManager() {
        itemTextures = new HashMap<>();
        itemTextures.put("log", Raylib.LoadTexture("assets/textures/item_log.png"));
        itemTextures.put("charcoal", Raylib.LoadTexture("assets/textures/item_charcoal.png"));
        itemTextures.put("crafting_station", Raylib.LoadTexture("assets/textures/item_crafting_station.png"));
    }

    @Override
    public Raylib.Texture getAsset(String assetId) {
        return itemTextures.get(assetId);
    }

    @Override
    public void dispose() {
        for (Raylib.Texture texture : itemTextures.values()) {
            Raylib.UnloadTexture(texture);
        }
    }
}
