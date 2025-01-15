package dev.spruce.game.world;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.graphics.Camera;

import dev.spruce.game.graphics.Window;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Map implements Serializable {

    protected Tile[][] tiles;
    protected int width, height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
    }

    public abstract void generate(GameState gameState);

    public abstract float getSpawnX();
    public abstract float getSpawnY();

    public void render(Graphics graphics, Camera camera) {
        Graphics2D g2d = (Graphics2D) graphics;

        int screenWidth = Window.getInstance().getWidth();
        int screenHeight = Window.getInstance().getHeight();

        int startX = (int) Math.max(0, camera.getX() / Tile.SIZE);
        int startY = (int) Math.max(0, camera.getY() / Tile.SIZE);
        int endX = (int) Math.min(width, (camera.getX() + screenWidth) / Tile.SIZE + 1);
        int endY = (int) Math.min(height, (camera.getY() + screenHeight) / Tile.SIZE + 1);

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile tile = tiles[x][y];
                BufferedImage texture = Assets.getInstance().getTileTextures().getAsset(tile.getId());
                g2d.drawImage(texture,
                        (int) ((x * tile.getSize()) - camera.getX()),
                        (int) ((y * tile.getSize()) - camera.getY()),
                        tile.getSize(), tile.getSize(), null
                );

                if (Game.debug) {
                    graphics.setColor(Color.red);
                    graphics.drawRect((int) ((x * tile.getSize()) - camera.getX()), (int) ((y * tile.getSize()) - camera.getY()), tile.getSize(), tile.getSize());
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
}
