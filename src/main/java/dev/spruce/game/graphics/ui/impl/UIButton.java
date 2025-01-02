package dev.spruce.game.graphics.ui.impl;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.UIElement;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;

import java.awt.*;

public class UIButton extends UIElement implements IMouseInput {

    private String text;
    private Color color;
    private Runnable action;

    public UIButton(String text, Color color, int x, int y, int width, int height, ScreenSnapPoint snapPoint, Runnable action) {
        super(x, y, width, height, snapPoint);
        this.action = action;
        this.text = text;
        this.color = color;
        InputManager.getInstance().subscribe(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        FontRenderer.drawStringCentred(graphics, text, getX() + getWidth() / 2, getY() + getHeight() / 2, Color.WHITE, Fonts.DEFAULT);
    }

    @Override
    public void onMousePress(int button, int x, int y) {

    }

    @Override
    public void onMouseRelease(int button, int x, int y) {

    }

    @Override
    public void onMouseClick(int button, int x, int y) {
        if (InputManager.getInstance().isMouseOver(getX(), getY(), getWidth(), getHeight())) {
            action.run();
        }
    }
}
