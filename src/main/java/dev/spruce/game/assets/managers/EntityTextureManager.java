package dev.spruce.game.assets.managers;

import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.assets.ImageBundle;
import dev.spruce.game.util.ImageUtils;

import java.util.HashMap;

public class EntityTextureManager extends AssetManager<String, ImageBundle> {

    private final HashMap<String, ImageBundle> entityTextures;

    public EntityTextureManager() {
        entityTextures = new HashMap<>();
        entityTextures.put("acacia_tree", new ImageBundle(ImageUtils.loadImage("assets/textures/entity_acacia_tree.png")));
    }

    @Override
    public ImageBundle getAsset(String assetId) {
        return entityTextures.get(assetId);
    }
}
