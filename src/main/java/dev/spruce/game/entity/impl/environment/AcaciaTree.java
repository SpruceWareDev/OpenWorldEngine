package dev.spruce.game.entity.impl.environment;

import dev.spruce.game.assets.Textures;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;

import java.awt.*;

public class AcaciaTree extends Tree {

    public AcaciaTree(GameState gameState, float x, float y) {
        super(gameState, x, y, 64, 128, 120);
        this.setEntityCollider(new EntityCollider(this, new Rectangle(10, 84, 44, 44)));
        enableCollision();
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        // Render from origin (bottom center)
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        graphics.drawImage(Textures.ACACIA_TREE, x, y, (int) getWidth(), (int) getHeight(), null);
    }
}
