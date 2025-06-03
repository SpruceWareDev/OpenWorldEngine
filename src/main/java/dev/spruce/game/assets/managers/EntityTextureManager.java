package dev.spruce.game.assets.managers;

import com.raylib.Raylib;
import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.assets.ImageBundle;
import dev.spruce.game.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class EntityTextureManager extends AssetManager<String, ImageBundle> {

    private final HashMap<String, ImageBundle> entityTextures;

    public EntityTextureManager() {
        entityTextures = new HashMap<>();
        entityTextures.put("acacia_tree", new ImageBundle(Raylib.LoadTexture("assets/textures/entity_acacia_tree.png")));

        // TODO: Add back player textures at a later time.
        /*
        // Player
        BufferedImage playerSheet = ImageUtils.loadImage("assets/textures/entity_player_idle.png");
        entityTextures.put("player_idle", new ImageBundle(
            ImageUtils.cropImage(playerSheet, 0, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 150, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 300, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 450, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 600, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 750, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 900, 0, 150, 150),
            ImageUtils.cropImage(playerSheet, 1050, 0, 150, 150)
        ));

         */
    }

    @Override
    public ImageBundle getAsset(String assetId) {
        return entityTextures.get(assetId);
    }

    @Override
    public void dispose() {
        for (ImageBundle imageBundle : entityTextures.values()) {
            imageBundle.dispose();
        }
    }
}
