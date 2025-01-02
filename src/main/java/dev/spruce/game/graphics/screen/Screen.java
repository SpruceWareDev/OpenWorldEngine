package dev.spruce.game.graphics.screen;

import java.awt.*;

public abstract class Screen {
    public abstract void init();
    public abstract void update(double delta);
    public abstract void render(Graphics graphics);
    public abstract void dispose();
}
