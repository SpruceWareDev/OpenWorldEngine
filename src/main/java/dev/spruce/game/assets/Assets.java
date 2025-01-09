package dev.spruce.game.assets;

import dev.spruce.game.assets.managers.EntityTextureManager;
import dev.spruce.game.assets.managers.TileTextureManager;
import dev.spruce.game.world.TileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Assets {

    private static Assets instance;

    private final TileTextureManager tileTextureManager;
    private final EntityTextureManager entityTextureManager;

    private Assets() {
        tileTextureManager = new TileTextureManager();
        entityTextureManager = new EntityTextureManager();
    }

    public TileTextureManager getTileAssets() {
        return tileTextureManager;
    }

    public EntityTextureManager getEntityAssets() {
        return entityTextureManager;
    }

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }
}
