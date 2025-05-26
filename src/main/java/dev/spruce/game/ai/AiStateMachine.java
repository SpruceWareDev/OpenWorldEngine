package dev.spruce.game.ai;

import java.io.Serializable;

public class AiStateMachine implements Serializable {

    private AiState currentState;
    private AiState nextState;

    public AiStateMachine() {
        this.currentState = AiState.IDLE;
        this.nextState = AiState.IDLE;
    }

    public void transitionTo(AiState state, AiState newNextState) {
        this.currentState = state;
        this.nextState = newNextState;
    }

    public void transition(AiState newNextState) {
        this.currentState = nextState;
        nextState = newNextState;
    }

    public void transition() {
        this.currentState = nextState;
    }

    public void setNextState(AiState newNextState) {
        this.nextState = newNextState;
    }

    public AiState getCurrentState() {
        return currentState;
    }

    public AiState getNextState() {
        return nextState;
    }
}
