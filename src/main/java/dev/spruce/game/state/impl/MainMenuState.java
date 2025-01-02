package dev.spruce.game.state.impl;

import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.state.State;

import java.awt.*;

public class MainMenuState extends State {

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, "Balls :3",
                Window.getInstance().getWidth() / 2,
                Window.getInstance().getHeight() / 4,
                Color.WHITE, Fonts.TITLE
        );
    }

    @Override
    public void dispose() {

    }
}
