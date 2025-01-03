package dev.spruce.game.assets;

import dev.spruce.game.world.TileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Textures {

    private static Textures instance;

    private HashMap<Integer, BufferedImage> tiles;

    private Textures() {
        tiles = new HashMap<>();
        tiles.put(TileManager.getInstance().GRASS.getId(), loadImage("assets/textures/tile_grass.png"));
        tiles.put(TileManager.getInstance().CRACKED_STONE.getId(), loadImage("assets/textures/tile_cracked_stone.png"));
        tiles.put(TileManager.getInstance().WATER.getId(), loadImage("assets/textures/tile_water.png"));
    }

    // Entities
    public static final BufferedImage ACACIA_TREE = loadImage("assets/textures/entity_acacia_tree.png");

    private static BufferedImage loadImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage getTileTexture(int id) {
        return tiles.get(id);
    }

    public static Textures getInstance() {
        if (instance == null) {
            instance = new Textures();
        }
        return instance;
    }
}
