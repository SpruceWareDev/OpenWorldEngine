package dev.spruce.game.entity.impl.environment;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.Interactable;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.item.Item;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.item.Items;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AcaciaTree extends DamageableEntity {

    public AcaciaTree(float x, float y) {
        super(x, y, 64, 128, 20);
        this.setEntityCollider(new EntityCollider(this, new Rectangle(10, 84, 44, 44)));
        enableCollision();
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Camera camera) {
        Raylib.Texture texture = Assets.getInstance().getEntityTextures().getAsset("acacia_tree").getSingle().get();
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        RenderUtils.drawTextureScaled(texture, x, y, (int) getWidth(), (int) getHeight(), Colors.WHITE);
    }

    @Override
    public void onDeath() {
        Game.getStateManager().getGameState().ifPresent(gameState -> {
            gameState.getEntityManager().spawn(new ItemStackEntity(new ItemStack(Items.LOG), getX(), getY()));
            gameState.getEntityManager().despawn(this);
        });
    }
}
