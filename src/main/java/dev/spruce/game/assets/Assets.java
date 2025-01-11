package dev.spruce.game.assets;

import dev.spruce.game.assets.managers.EntityTextureManager;
import dev.spruce.game.assets.managers.ItemTextureManager;
import dev.spruce.game.assets.managers.TileTextureManager;

public class Assets {

    private static Assets instance;

    private final TileTextureManager tileTextureManager;
    private final EntityTextureManager entityTextureManager;
    private final ItemTextureManager itemTextureManager;

    private Assets() {
        tileTextureManager = new TileTextureManager();
        entityTextureManager = new EntityTextureManager();
        itemTextureManager = new ItemTextureManager();
    }

    public TileTextureManager getTileTextures() {
        return tileTextureManager;
    }

    public EntityTextureManager getEntityTextures() {
        return entityTextureManager;
    }

    public ItemTextureManager getItemTextures() {
        return itemTextureManager;
    }

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }
}
