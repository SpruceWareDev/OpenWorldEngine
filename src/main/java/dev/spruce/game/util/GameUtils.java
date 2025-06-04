package dev.spruce.game.util;

import dev.spruce.game.Game;
import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.impl.Player;
import dev.spruce.game.graphics.particle.Particle;

/**
 * Utility class for game-related functions.
 * This util class can be used to implement various helper methods.
 * This will help reduce code duplication when accessing game state.
 * Probably a bit crap but honestly who cares at this point.
 */
public class GameUtils {

    public static void spawnParticle(Particle particle) {
        Game.getStateManager().getGameState().ifPresent(gameState ->
                gameState.getParticleRenderer().spawnParticle(particle)
        );
    }

    public static void spawnEntity(Entity entity) {
        Game.getStateManager().getGameState().ifPresent(gameState ->
                gameState.getEntityManager().spawn(entity)
        );
    }

    public static void despawnEntity(Entity entity) {
        Game.getStateManager().getGameState().ifPresent(gameState ->
                gameState.getEntityManager().despawn(entity)
        );
    }

    public static Player getPlayer() {
        if (Game.getStateManager().getGameState().isPresent()) {
            return Game.getStateManager().getGameState().get().getPlayer();
        }
        throw new RuntimeException("Tried to access player while not in game state!");
    }
}
