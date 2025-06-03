package dev.spruce.game.entity;

import dev.spruce.game.Game;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.entity.impl.hostile.HostileEntity;
import dev.spruce.game.entity.impl.projectile.Projectile;
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
        this.entities = new CopyOnWriteArrayList<>();
        this.gameState = gameState;
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
            if (!shouldUpdate(entity))
                continue;

            entity.update(delta);
            entity.updateParticles();

            if (entity instanceof DamageableEntity damageable) {
                damageable.handlePassiveDamage();
            }
        }
    }

    public void render(Camera camera) {
        for (Entity entity : entities) {
            if (!entity.isEntityOnScreen(gameState.getCamera()))
                continue;
            entity.render(camera);

            if (Game.debug) {
                entity.renderBoundingBox(camera);
            }
        }
    }

    public void dispose() {
        entities.clear();
    }

    private boolean shouldUpdate(Entity entity) {
        return (entity instanceof HostileEntity) ||
               (entity instanceof Player) ||
                (entity instanceof Projectile) ||
               entity.isEntityOnScreen(gameState.getCamera());
    }

    public List<Entity> getOnScreenEntities() {
        return entities.stream().filter(entity -> entity.isEntityOnScreen(gameState.getCamera())).toList();
    }

    public CopyOnWriteArrayList<Entity> getEntities() {
        return entities;
    }
}
