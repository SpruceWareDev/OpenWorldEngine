package dev.spruce.game.world.entity.impl.environment;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.util.EntityCollider;
import dev.spruce.game.util.RenderUtils;
import dev.spruce.game.world.entity.DamageableEntity;

import java.awt.*;

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
            gameState.getEntityManager().despawn(this);
        });
    }
}
