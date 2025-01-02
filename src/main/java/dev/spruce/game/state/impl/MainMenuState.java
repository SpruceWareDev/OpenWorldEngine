package dev.spruce.game.state.impl;

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
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 1280, 720);
    }

    @Override
    public void dispose() {

    }
}
