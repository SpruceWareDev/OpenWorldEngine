package dev.spruce.game.graphics.screen.impl;

import dev.spruce.game.graphics.screen.Screen;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;

public class CraftingStationScreen extends Screen {

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        RenderUtils.drawRect(graphics, 100, 100, 100, 100, Color.GRAY);
    }

    @Override
    public void dispose() {

    }
}
