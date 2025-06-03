package dev.spruce.game.state.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.state.State;

public class MainMenuState extends State {

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render() {
        Raylib.DrawText(Game.FORMATTED_NAME, 100, 100, 22, Colors.WHITE);
    }

    @Override
    public void dispose() {

    }
}
