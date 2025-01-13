package dev.spruce.game.graphics.ui.hud;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
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
        int slots = GameState.getPlayer().getInventory().getCapacity();
        int hotbarX = (screenW / 2) - ((slots * (itemSlotSize + 4)) / 2);
        int hotbarY = screenH - (itemSlotSize + 54);
        for (int i = 0; i < slots; i++) {
            int x = hotbarX + (i * (itemSlotSize + 4));
            graphics.setColor(new Color(0,0,0,128));
            graphics.fillRect(x, hotbarY, itemSlotSize, itemSlotSize);

            if (!GameState.getPlayer().getInventory().isSlotEmpty(i)) {
                ItemStack stack = GameState.getPlayer().getInventory().getSlot(i);
                // Draw item image
                graphics2D.drawImage(
                        Assets.getInstance().getItemTextures().getAsset(stack.getItem().getName()),
                        x, hotbarY, itemSlotSize, itemSlotSize, null
                );
                // Draw stack amount
                FontRenderer.drawString(
                        graphics, String.valueOf(stack.getQuantity()),
                        x + itemSlotSize - 12, hotbarY + itemSlotSize - 12,
                        false, Color.white, Fonts.DEFAULT
                );
            }
        }

        // Render health
        int healthWidth = (((itemSlotSize + 4) * slots) / 2) * (GameState.getPlayer().getHealth() / GameState.getPlayer().getMaxHealth());
        graphics.setColor(Color.RED);
        graphics.fillRect(hotbarX, hotbarY - 4, healthWidth, 2);
    }
}
