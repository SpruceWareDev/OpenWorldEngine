package dev.spruce.game.graphics.ui.component.impl;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIElement;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.InputManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UITextBox extends UIElement implements IKeyInput {

    private StringBuilder text;

    public UITextBox(int x, int y, int width, int height, ScreenSnapPoint snapPoint) {
        super(x, y, width, height, snapPoint);
        text = new StringBuilder();
        InputManager.getInstance().subscribeKey(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        FontRenderer.drawString(graphics, text.toString(), getX() + 5, getY() + 5, false, Color.WHITE, Fonts.DEFAULT);
    }

    @Override
    public void onKeyPress(int keyCode) {

    }

    @Override
    public void onKeyRelease(int keyCode) {

    }

    @Override
    public void onKeyTyped(int keyCode, char keyChar) {
        if (keyCode == KeyEvent.VK_BACK_SPACE && text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
            return;
        }
        if (!String.valueOf(keyChar).matches("[a-zA-Z0-9]")) return;
        text.append(keyChar);
    }

    @Override
    public void dispose() {
        InputManager.getInstance().unsubscribeKey(this);
    }

    public String getText() {
        return text.toString();
    }
}
