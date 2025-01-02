package dev.spruce.game.entity.impl.environment;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.state.impl.GameState;

public abstract class Tree extends Entity {

    public Tree(GameState gameState, float x, float y, float width, float height, int health) {
        super(gameState, x, y, width, height, health);
    }
}
