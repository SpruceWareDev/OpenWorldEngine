package dev.spruce.game.state;

import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.impl.GameState;

import java.util.Optional;

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

    public void render() {
        if (!finishedLoading)
            return;
        currentState.render();
    }

    public void setState(State state) {
        finishedLoading = false;
        currentState.dispose();
        currentState = state;
        InputManager.getInstance().unsubscribeAll();
        currentState.init();
        finishedLoading = true;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    /*
    public GameState getGameState() {
        if (!(currentState instanceof GameState))
            throw new RuntimeException("Tried to access game state from another state!");
        return (GameState) currentState;
    }

     */

    public Optional<GameState> getGameState() {
        if (currentState instanceof GameState gameState) {
            return Optional.of(gameState);
        }
        return Optional.empty();
    }

    public boolean isGameStateActive() {
        return currentState instanceof GameState;
    }
}
