package dev.spruce.game.graphics.screen.impl;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.screen.Screen;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;

public class CraftingStationScreen extends Screen {

    private static final int GUI_WIDTH = 600;
    private static final int GUI_HEIGHT = 400;

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        int windowWidth = Window.getInstance().getWidth();
        int windowHeight = Window.getInstance().getHeight();

        // GUI background
        RenderUtils.drawRect(graphics,
                (windowWidth / 2f) - (GUI_WIDTH / 2f),
                (windowHeight / 2f) - (GUI_HEIGHT / 2f),
                GUI_WIDTH, GUI_HEIGHT, Color.DARK_GRAY
        );
        RenderUtils.drawRect(graphics,
                (windowWidth / 2f) - (GUI_WIDTH / 2f) + 10,
                (windowHeight / 2f) - (GUI_HEIGHT / 2f) + 10,
                GUI_WIDTH - 20, GUI_HEIGHT - 20, Color.GRAY
        );

        // GUI title
        FontRenderer.drawStringCentred(graphics, "Crafting Station", windowWidth / 2, (int) ((windowHeight / 2f) - (GUI_HEIGHT / 2f) + 20), Color.WHITE, Fonts.LARGE);
    }

    @Override
    public void dispose() {

    }
}
