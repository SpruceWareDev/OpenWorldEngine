package dev.spruce.game.graphics.ui.hud;

import dev.spruce.game.graphics.Window;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.io.Serializable;

public class InGameHUD implements Serializable {

    private final GameState gameState;

    private final int itemSlotSize = 56;

    public InGameHUD(GameState gameState) {
        this.gameState = gameState;
    }

    public void update(double delta) {

    }

    public void render(Graphics graphics) {
        int screenW = Window.getInstance().getWidth();
        int screenH = Window.getInstance().getHeight();

        // Render hotbar
        int slots = gameState.getPlayer().getInventory().getCapacity();
        for (int i = 0; i < slots; i++) {
            int x = (screenW / 2) - ((slots * (itemSlotSize + 4)) / 2) + (i * (itemSlotSize + 4));
            int y = screenH - (itemSlotSize + 54);
            graphics.setColor(new Color(0,0,0,128));
            graphics.fillRect(x, y, itemSlotSize, itemSlotSize);
        }
    }
}
