package dev.spruce.game.world;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tile implements Serializable {

    public static final int SIZE = 64;

    private int id;
    private int size;
    private boolean solid;

    public Tile(int id, int size, boolean solid) {
        this.id = id;
        this.size = size;
        this.solid = solid;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public boolean isSolid() {
        return solid;
    }
}
