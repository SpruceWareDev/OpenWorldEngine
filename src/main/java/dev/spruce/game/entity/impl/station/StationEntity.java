package dev.spruce.game.entity.impl.station;

import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Interactable;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.world.Tile;

import java.awt.*;

public abstract class StationEntity extends DamageableEntity implements Interactable {

    public static final int STATION_HEALTH = 30;

    protected final StationType type;

    public StationEntity(StationType type, float x, float y) {
        super(x, y, Tile.SIZE, Tile.SIZE, STATION_HEALTH);
        this.type = type;
        enableCollision();
    }

    public StationType getType() {
        return type;
    }
}
