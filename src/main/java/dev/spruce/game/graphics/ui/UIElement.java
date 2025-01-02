package dev.spruce.game.graphics.ui;

import java.awt.*;

public abstract class UIElement {

    private int x, y, width, height;
    private ScreenSnapPoint snapPoint;

    public UIElement(int x, int y, int width, int height, ScreenSnapPoint snapPoint) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.snapPoint = snapPoint;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

    public void dispose() {}

    public ScreenSnapPoint getSnapPoint() {
        return snapPoint;
    }

    public void setSnapPoint(ScreenSnapPoint snapPoint) {
        this.snapPoint = snapPoint;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
