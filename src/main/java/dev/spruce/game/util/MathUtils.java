package dev.spruce.game.util;

import dev.spruce.game.entity.Entity;

public class MathUtils {

    public static boolean isWithinDistance(Entity entity, Entity entity1, float distance) {
        float entityCentreX = (float)
                (entity.getX()
                + entity.getEntityCollider().getBounds().getX()
                + entity.getEntityCollider().getBounds().getWidth() / 2
                );
        float entityCentreY = (float)
                (entity.getY()
                        + entity.getEntityCollider().getBounds().getY()
                        + entity.getEntityCollider().getBounds().getHeight() / 2
                );
        float entity1CentreX = (float)
                (entity1.getX()
                        + entity1.getEntityCollider().getBounds().getX()
                        + entity1.getEntityCollider().getBounds().getWidth() / 2
                );
        float entity1CentreY = (float)
                (entity1.getY()
                        + entity1.getEntityCollider().getBounds().getY()
                        + entity1.getEntityCollider().getBounds().getHeight() / 2
                );

        double x = Math.pow(entityCentreX - entity1CentreX, 2);
        double y = Math.pow(entityCentreY - entity1CentreY, 2);
        return Math.abs(Math.sqrt(x + y)) <= distance;
    }
}
