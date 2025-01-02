package dev.spruce.game.world;

import java.awt.image.BufferedImage;

public class Tile {

    public static final int SIZE = 64;

    private int id;
    private int size;
    private boolean solid;
    private BufferedImage texture;

    public Tile(int id, int size, boolean solid, BufferedImage texture) {
        this.id = 0;
        this.size = size;
        this.solid = solid;
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public int getSize() {
        return size;
    }

    public boolean isSolid() {
        return solid;
    }
}
