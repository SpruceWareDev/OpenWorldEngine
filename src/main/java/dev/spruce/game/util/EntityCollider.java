package dev.spruce.game.util;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;

import java.awt.*;
import java.io.Serializable;

public class EntityCollider implements Serializable {

    private Entity entity;
    private Rectangle bounds;

    public EntityCollider(Entity entity, Rectangle bounds) {
        this.entity = entity;
        this.bounds = bounds;
    }

    public boolean checkCollision(EntityCollider other) {
        return bounds.intersects(other.getBounds());
    }

    public boolean checkCollision(Entity other, float offsetX, float offsetY) {
        if (other.equals(entity) || !other.hasCollisions())
            return false;

        Rectangle otherBounds = new Rectangle(
                (int) (other.getX() + other.getEntityCollider().getBounds().x),
                (int) (other.getY() + other.getEntityCollider().getBounds().y),
                (int) other.getEntityCollider().bounds.width,
                (int) other.getEntityCollider().bounds.height);

        return otherBounds.intersects(
                new Rectangle(
                    (int) (entity.getX() + bounds.x + offsetX),
                    (int) (entity.getY() + bounds.y + offsetY),
                    (int) bounds.width,
                    (int) bounds.height
                )
        );
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
