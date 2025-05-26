package dev.spruce.game.util;

import dev.spruce.game.entity.Entity;

import java.util.Random;

public class MathUtils {

    public static final Random RANDOM = new Random();

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

    /**
     * Linearly interpolates between two values a and b by a factor t.
     *
     * @param a The starting value.
     * @param b The ending value.
     * @param t The interpolation factor (0.0 to 1.0).
     * @return The interpolated value.
     */
    public static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    /**
     * Calculates the angle between two entities in radians.
     *
     * @param entity1 The first entity.
     * @param entity2 The second entity.
     * @return The angle in radians.
     */
    public static float getAngle(Entity entity1, Entity entity2) {
        float deltaX = (float) (entity2.getX() - entity1.getX());
        float deltaY = (float) (entity2.getY() - entity1.getY());
        return (float) Math.atan2(deltaY, deltaX);
    }

    /**
     * Generates a random float between min (inclusive) and max (exclusive).
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random float between min and max.
     */
    public static float randomFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }
}
