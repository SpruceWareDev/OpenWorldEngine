package dev.spruce.game.entity.impl.environment;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.Interactable;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.sound.effect.AudioEffect;
import dev.spruce.game.sound.effect.impl.BitCrushEffect;
import dev.spruce.game.sound.effect.impl.ReverbEffect;
import dev.spruce.game.state.impl.GameState;

import javax.sound.sampled.AudioFormat;
import java.awt.*;

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
    public void render(Graphics graphics, Camera camera) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(
                Assets.getInstance().getItemTextures().getAsset(itemStack.getItem().getName()),
                (int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight(), null
        );
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void interact() {
        if (GameState.getPlayer().getInventory().addItem(itemStack)) {
            GameState.getEntityManager().despawn(this);
        }
    }
}
