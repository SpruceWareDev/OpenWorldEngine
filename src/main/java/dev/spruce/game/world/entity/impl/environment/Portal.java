package dev.spruce.game.world.entity.impl.environment;

import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.Interactable;

public abstract class Portal extends Entity implements Interactable {

    public Portal(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
