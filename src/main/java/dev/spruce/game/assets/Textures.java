package dev.spruce.game.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Textures {

    // Tiles
    public static final BufferedImage GRASS = loadImage("assets/textures/tile_grass.png");
    public static final BufferedImage CRACKED_STONE = loadImage("assets/textures/tile_cracked_stone.png");

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
}
