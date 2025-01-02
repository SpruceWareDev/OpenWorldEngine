package dev.spruce.game.state;

import java.awt.*;

public abstract class State {

    public abstract void init();

    public abstract void update(double delta);

    public abstract void render(Graphics graphics);

    public abstract void dispose();
}
