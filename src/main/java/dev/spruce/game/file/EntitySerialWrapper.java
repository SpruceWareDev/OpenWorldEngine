package dev.spruce.game.file;

import dev.spruce.game.entity.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntitySerialWrapper implements Serializable {

    private final List<Entity> entities;

    public EntitySerialWrapper(CopyOnWriteArrayList<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
