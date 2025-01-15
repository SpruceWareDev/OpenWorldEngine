package dev.spruce.game.graphics.ui.component.impl;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIElement;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UITextBox extends UIElement implements IKeyInput, IMouseInput {

    private final StringBuilder text;
    private boolean focused;
    private final AllowedChars allowedChars;

    public UITextBox(int x, int y, int width, int height, ScreenSnapPoint snapPoint, AllowedChars allowedChars) {
        super(x, y, width, height, snapPoint);
        text = new StringBuilder();
        this.allowedChars = allowedChars;
        InputManager.getInstance().subscribeKey(this);
        InputManager.getInstance().subscribeMouse(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.darkGray);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        if (focused) {
            graphics.setColor(Color.BLUE);
            graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        }
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
        if (!focused) return;
        if (keyCode == KeyEvent.VK_BACK_SPACE && !text.isEmpty()) {
            text.deleteCharAt(text.length() - 1);
            return;
        }
        if (!String.valueOf(keyChar).matches(allowedChars.getRegex())) return;
        text.append(keyChar);
    }

    @Override
    public void dispose() {
        InputManager.getInstance().unsubscribeKey(this);
        InputManager.getInstance().unsubscribeMouse(this);
    }

    public String getText() {
        return text.toString();
    }

    @Override
    public void onMousePress(int button, int x, int y) {
        focused = x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    @Override
    public void onMouseRelease(int button, int x, int y) {

    }

    @Override
    public void onMouseClick(int button, int x, int y) {

    }

    public enum AllowedChars {
        ALPHA_NUMERIC("[a-zA-Z0-9]"),
        ALPHA("[a-zA-Z]"),
        NUMERIC("[0-9]"),
        ALPHA_NUMERIC_SPACE("[a-zA-Z0-9 ]"),
        ALPHA_SPACE("[a-zA-Z ]"),
        NUMERIC_SPACE("[0-9 ]");

        private final String regex;

        AllowedChars(String regex) {
            this.regex = regex;
        }

        public String getRegex() {
            return regex;
        }
    }
}
