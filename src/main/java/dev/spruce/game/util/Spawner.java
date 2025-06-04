package dev.spruce.game.util;

import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.impl.Player;
import dev.spruce.game.world.entity.impl.hostile.HostileEntity;
import dev.spruce.game.world.entity.impl.hostile.TestEnemy;
import dev.spruce.game.state.impl.GameState;

public class Spawner {

    private final GameState gameState;

    private int spawnIntervalTicks = 500;
    private int spawnTimer = 0;

    private Entity lastSpawnedHostile = null;

    public Spawner(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        spawnIntervalTicks = 500 - (Math.min(gameState.getDifficulty() * 100, 400));

        if (spawnTimer >= spawnIntervalTicks) {
            Player player = gameState.getPlayer();
            int x = (int) player.getX() + (int) (Math.random() * 1000 - 500);
            int y = (int) player.getY() + (int) (Math.random() * 1000 - 500);

            HostileEntity entity = getRandomHostile(x, y);
            gameState.getEntityManager().spawn(entity);
            spawnTimer = 0;
        }
        spawnTimer++;
    }

    private HostileEntity getRandomHostile(int x, int y) {
        if (lastSpawnedHostile == null) {
            return new TestEnemy(x, y);
        }
        return new TestEnemy(x, y);
    }
}
