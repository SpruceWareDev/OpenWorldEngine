package dev.spruce.game.entity.impl.environment;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AcaciaTree extends Entity {

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
        BufferedImage texture = Assets.getInstance().getEntityTextures().getAsset("acacia_tree").getSingle().get();
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        graphics.drawImage(texture, x, y, (int) getWidth(), (int) getHeight(), null);
    }
}
