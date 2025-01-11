package dev.spruce.game.assets.managers;

import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ItemTextureManager extends AssetManager<String, BufferedImage> {

    private final HashMap<String, BufferedImage> itemTextures;

    public ItemTextureManager() {
        itemTextures = new HashMap<>();
        itemTextures.put("log", ImageUtils.loadImage("assets/textures/item_log.png"));
        itemTextures.put("charcoal", ImageUtils.loadImage("assets/textures/item_charcoal.png"));
    }

    @Override
    public BufferedImage getAsset(String assetId) {
        return itemTextures.get(assetId);
    }
}
