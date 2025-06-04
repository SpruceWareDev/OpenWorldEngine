package dev.spruce.game.state;

public abstract class State {

    public abstract void init();

    public abstract void update(double delta);

    public abstract void render();

    public abstract void dispose();
}
