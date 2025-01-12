package dev.spruce.game.entity;

import dev.spruce.game.Game;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager {

    private final GameState gameState;

    private final CopyOnWriteArrayList<Entity> entities;

    public EntityManager(GameState gameState) {
        this.gameState = gameState;
        entities = new CopyOnWriteArrayList<>();
    }

    public void spawn(Entity entity) {
        entities.add(entity);
    }

    public void despawn(Entity entity) {
        entities.remove(entity);
    }

    public void update(double delta) {
        // Sort entities based on y position
        entities.sort(Comparator.comparingDouble(o -> (o.getY() + o.getEntityCollider().getBounds().y)));

        // Update entities
        for (Entity entity : entities) {
            if (!entity.isEntityOnScreen())
                continue;

            entity.update(delta);
        }
    }

    public void render(Graphics graphics, Camera camera) {
        for (Entity entity : entities) {
            if (!entity.isEntityOnScreen())
                continue;
            entity.render(graphics, camera);

            if (Game.debug) {
                entity.renderBoundingBox(graphics, camera);
            }
        }
    }

    public void dispose() {
        entities.clear();
    }

    public List<Entity> getOnScreenEntities() {
        return entities.stream().filter(Entity::isEntityOnScreen).toList();
    }

    public CopyOnWriteArrayList<Entity> getEntities() {
        return entities;
    }
}
