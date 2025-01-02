package dev.spruce.game.state;

import dev.spruce.game.input.InputManager;

import java.awt.*;

public class StateManager {

    private State currentState;

    public StateManager(State initialState) {
        currentState = initialState;
        currentState.init();
    }

    public void update(double delta) {
        currentState.update(delta);
    }

    public void render(Graphics graphics) {
        currentState.render(graphics);
    }

    public void setState(State state) {
        currentState.dispose();
        InputManager.getInstance().unsubscribeAll();
        currentState = state;
        currentState.init();
    }
}
