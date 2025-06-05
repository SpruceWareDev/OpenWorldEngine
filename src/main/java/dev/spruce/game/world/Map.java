package dev.spruce.game.world;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.RenderUtils;

import java.io.Serializable;

public abstract class Map implements Serializable {

    protected Tile[][] tiles;
    protected int width, height;
    protected int seed;

    public Map(int width, int height, int seed) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
    }

    public abstract void generate(GameState gameState);

    public abstract float getSpawnX();
    public abstract float getSpawnY();

    public void render(Camera camera) {
        int screenWidth = Raylib.GetRenderWidth();
        int screenHeight = Raylib.GetRenderHeight();

        int startX = (int) Math.max(0, camera.getX() / Tile.SIZE);
        int startY = (int) Math.max(0, camera.getY() / Tile.SIZE);
        int endX = (int) Math.min(width, (camera.getX() + screenWidth) / Tile.SIZE + 1);
        int endY = (int) Math.min(height, (camera.getY() + screenHeight) / Tile.SIZE + 1);

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile tile = tiles[x][y];
                Raylib.Texture texture = Assets.getInstance().getTileTextures().getAsset(tile.getId());

                RenderUtils.drawTextureScaled(texture,
                        (int) ((x * tile.getSize()) - camera.getX()),
                        (int) ((y * tile.getSize()) - camera.getY()),
                        tile.getSize(), tile.getSize(), Colors.WHITE
                );

                /*
                if (Game.debug) {
                    graphics.setColor(Color.red);
                    graphics.drawRect((int) ((x * tile.getSize()) - camera.getX()), (int) ((y * tile.getSize()) - camera.getY()), tile.getSize(), tile.getSize());
                }
                 */
            }
        }
    }

    protected void fillMap(Tile tile) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = tile;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSeed() {
        return seed;
    }
}
