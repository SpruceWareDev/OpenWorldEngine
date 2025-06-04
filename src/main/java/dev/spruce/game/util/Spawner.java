package dev.spruce.game.util;

import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.impl.Player;
import dev.spruce.game.world.entity.impl.hostile.HostileEntity;
import dev.spruce.game.world.entity.impl.hostile.TestEnemy;
import dev.spruce.game.state.impl.GameState;

import java.util.ArrayList;
import java.util.List;

public class Spawner {

    private final GameState gameState;

    private static final int BASE_SPAWN_INTERVAL_TICKS = TimerUtils.ticksFromSeconds(10f);
    private int spawnTimer = 0;

    private static final int BASE_SPAWN_TOKENS = 10;
    private Entity lastSpawnedHostile = null;

    public Spawner(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        int spawnIntervalTicks = BASE_SPAWN_INTERVAL_TICKS -
                (Math.min(gameState.getDifficulty() * TimerUtils.ticksFromSeconds(1f),
                        TimerUtils.ticksFromSeconds(5f))
                );

        if (spawnTimer >= spawnIntervalTicks) {
            Player player = gameState.getPlayer();
            List<HostileEntity> hostiles = generateHostiles(player);
            for (HostileEntity hostile : hostiles) {
                gameState.getEntityManager().spawn(hostile);
                lastSpawnedHostile = hostile;
            }
            spawnTimer = 0;
        }
        spawnTimer++;
    }

    private List<HostileEntity> generateHostiles(Player player) {
        int spawnTokens = BASE_SPAWN_TOKENS + ((gameState.getDifficulty() ^ 2) * BASE_SPAWN_TOKENS);
        System.out.println("Spawning hostiles with " + spawnTokens + " tokens at player position: " + player.getX() + ", " + player.getY());
        List<HostileEntity> hostiles = new ArrayList<>();

        while (spawnTokens > 0) {
            int x = (int) player.getX() + (int) (Math.random() * 1000 - 500);
            int y = (int) player.getY() + (int) (Math.random() * 1000 - 500);

            HostileEntity hostile = getRandomHostile(spawnTokens, x, y);
            hostiles.add(hostile);
            spawnTokens -= hostile.getSpawnCost();
        }
        return hostiles;
    }

    private HostileEntity getRandomHostile(int spawnTokens, int x, int y) {
        return new TestEnemy(x, y);
    }
}
