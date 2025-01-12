package dev.spruce.game.state;

import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class StateManager {

    private State currentState;
    private boolean finishedLoading;

    private boolean paused;

    public StateManager(State initialState) {
        finishedLoading = false;
        currentState = initialState;
        currentState.init();
        finishedLoading = true;
        paused = false;
    }

    public void update(double delta) {
        if (!finishedLoading || paused)
            return;
        currentState.update(delta);
    }

    public void render(Graphics graphics) {
        if (!finishedLoading || paused)
            return;
        currentState.render(graphics);
    }

    public void setState(State state, boolean shouldInit) {
        finishedLoading = false;
        currentState.dispose();
        if (shouldInit) InputManager.getInstance().unsubscribeAll();
        currentState = state;
        if (shouldInit) currentState.init();
        finishedLoading = true;
    }

    public void togglePause() {
        paused = !paused;
    }
}
