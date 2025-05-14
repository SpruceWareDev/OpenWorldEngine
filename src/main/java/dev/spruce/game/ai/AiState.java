package dev.spruce.game.ai;

import java.io.Serializable;

public enum AiState implements Serializable {
    IDLE,
    ATTACKING,
    FLEEING,
    PATROLLING,
    CHASING;
}
