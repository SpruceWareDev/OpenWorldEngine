package dev.spruce.game.entity.impl.hostile;

import dev.spruce.game.Game;
import dev.spruce.game.ai.AiState;
import dev.spruce.game.ai.AiStateMachine;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.impl.projectile.Fireball;
import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.util.MathUtils;
import dev.spruce.game.world.Tile;

import java.awt.*;
import java.util.List;

public class TestEnemy extends HostileEntity {

    // TODO: Make standard move speeds for all entities in a static class
    private static final float MOVE_SPEED = 4.5f;

    // Timer values for attacking
    private static final int SHOOT_DELAY_TICKS = 120;
    private int shootTimerTicks = 0;

    private final AiStateMachine stateMachine;

    public TestEnemy(float x, float y) {
        super(x, y, 40, 40, 30);
        this.stateMachine = new AiStateMachine();
        enableCollision();
    }

    @Override
    public void update(double delta) {
        resetVelocity();
        handleAiState();

        switch (stateMachine.getCurrentState()) {
            case ATTACKING -> attack();
            case CHASING -> chase();
        }

        boolean collidingX = false, collidingY = false;
        List<Entity> onScreenEntities = Game.getStateManager().getGameState().getEntityManager().getOnScreenEntities();

        for (Entity entity : onScreenEntities) {
            if (getEntityCollider().checkCollision(entity, (float) (getDx() * delta * MOVE_SPEED), 0f))
                collidingX = true;
            if (getEntityCollider().checkCollision(entity, 0f, (float) (getDy() * delta * MOVE_SPEED)))
                collidingY = true;
        }

        applyVelocity(delta, MOVE_SPEED, collidingX, collidingY);
    }

    private void handleAiState() {
        if (MathUtils.isWithinDistance(this, Game.getStateManager().getGameState().getPlayer(), 4f * Tile.SIZE)) {
            stateMachine.transitionTo(AiState.ATTACKING, AiState.CHASING);
        } else if (stateMachine.getNextState().equals(AiState.CHASING)) {
            stateMachine.transition();
        }
    }

    private void attack() {
        if (shootTimerTicks <= 0) {
            float angle = MathUtils.getAngle(this, Game.getStateManager().getGameState().getPlayer());
            float dx = (float) Math.cos(angle) * Projectile.BASE_SPEED;
            float dy = (float) Math.sin(angle) * Projectile.BASE_SPEED;
            SoundManager.getInstance().playSound("fireball", 50);
            Game.getStateManager().getGameState().getEntityManager().spawn(new Fireball(this, getX(), getY(), dx, dy));
            shootTimerTicks = SHOOT_DELAY_TICKS;
        }
        shootTimerTicks--;
    }

    private void chase() {
        float angle = MathUtils.getAngle(this, Game.getStateManager().getGameState().getPlayer());
        float dx = (float) Math.cos(angle) * 1.5f;
        float dy = (float) Math.sin(angle) * 1.5f;
        setDx(dx);
        setDy(dy);
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.red);
        graphics.fillRect(
                (int) (getX() - camera.getX()),
                (int) (getY() - camera.getY()),
                (int) getWidth(), (int) getHeight()
        );
    }

    @Override
    public void onDeath() {
        Game.getStateManager().getGameState().getEntityManager().despawn(this);
    }
}
