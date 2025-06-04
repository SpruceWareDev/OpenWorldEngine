package dev.spruce.game.world.entity.impl.environment;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.util.RenderUtils;
import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.Interactable;
import dev.spruce.game.world.item.ItemStack;

public class ItemStackEntity extends Entity implements Interactable {

    private final ItemStack itemStack;

    public ItemStackEntity(ItemStack itemStack, float x, float y) {
        super(x, y, 32, 32);
        this.itemStack = itemStack;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Camera camera) {
        Raylib.Texture texture = Assets.getInstance().getItemTextures().getAsset(itemStack.getItem().getName());
        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        RenderUtils.drawTextureScaled(texture, x, y, (int) getWidth(), (int) getHeight(), Colors.WHITE);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void interact() {
        Game.getStateManager().getGameState().ifPresent(gameState -> {
            if (gameState.getPlayer().getInventory().addItem(itemStack)) {
                gameState.getEntityManager().despawn(this);
            }
        });
    }
}
