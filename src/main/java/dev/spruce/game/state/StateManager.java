package dev.spruce.game.state;

import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class StateManager {

    private State currentState;
    private boolean finishedLoading;

    public StateManager(State initialState) {
        finishedLoading = false;
        currentState = initialState;
        currentState.init();
        finishedLoading = true;
    }

    public void update(double delta) {
        if (!finishedLoading)
            return;
        currentState.update(delta);
    }

    public void render(Graphics graphics) {
        if (!finishedLoading)
            return;
        currentState.render(graphics);
    }

    public void setState(State state, boolean shouldInit) {
        finishedLoading = false;
        currentState.dispose();
        InputManager.getInstance().unsubscribeAll();
        currentState = state;
        currentState.init();
        finishedLoading = true;
    }
}
