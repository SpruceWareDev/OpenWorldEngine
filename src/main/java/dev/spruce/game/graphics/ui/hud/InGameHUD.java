package dev.spruce.game.graphics.ui.hud;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.io.Serializable;

public class InGameHUD {

    private final GameState gameState;

    private final int itemSlotSize = 56;

    public InGameHUD(GameState gameState) {
        this.gameState = gameState;
    }

    public void update(double delta) {

    }

    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        int screenW = Window.getInstance().getWidth();
        int screenH = Window.getInstance().getHeight();

        // Render hotbar
        int slots = gameState.getPlayer().getInventory().getCapacity();
        int hotbarX = (screenW / 2) - ((slots * (itemSlotSize + 4)) / 2);
        int hotbarY = screenH - (itemSlotSize + 54);
        for (int i = 0; i < slots; i++) {
            int x = hotbarX + (i * (itemSlotSize + 4));
            graphics.setColor(new Color(0,0,0,128));
            graphics.fillRect(x, hotbarY, itemSlotSize, itemSlotSize);

            if (!gameState.getPlayer().getInventory().isSlotEmpty(i)) {
                ItemStack stack = gameState.getPlayer().getInventory().getSlot(i);
                graphics2D.drawImage(
                        Assets.getInstance().getItemTextures().getAsset(stack.getItem().getName()),
                        x, hotbarY, itemSlotSize, itemSlotSize, null
                );
            }
        }

        // Render health
        int healthWidth = (((itemSlotSize + 4) * slots) / 2) * (gameState.getPlayer().getHealth() / gameState.getPlayer().getMaxHealth());
        graphics.setColor(Color.RED);
        graphics.fillRect(hotbarX, hotbarY - 4, healthWidth, 2);
    }
}
