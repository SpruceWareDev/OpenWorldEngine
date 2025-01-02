package dev.spruce.game.entity.impl;

import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.entity.impl.projectile.RockPellet;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.EntityCollider;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class Player extends Entity implements IMouseInput {

    public static final float PLAYER_SPEED = 5f;

    public Player(GameState gameState, float x, float y) {
        super(gameState, x, y, 32, 32, 100);
        InputManager.getInstance().subscribe(this);
    }

    @Override
    public void update(double delta) {
        move((float) delta);
    }

    private void move(float delta) {
        resetVelocity();
        if (InputManager.getInstance().isKeyDown(KeyEvent.VK_W)) {
            setDy(-1);
        } else if (InputManager.getInstance().isKeyDown(KeyEvent.VK_S)) {
            setDy(1);
        }
        if (InputManager.getInstance().isKeyDown(KeyEvent.VK_A)) {
            setDx(-1);
        } else if (InputManager.getInstance().isKeyDown(KeyEvent.VK_D)) {
            setDx(1);
        }

        boolean collidingX = false, collidingY = false;
        List<Entity> onScreenEntities = gameState.getEntityManager().getOnScreenEntities();

        for (Entity entity : onScreenEntities) {
            if (getEntityCollider().checkCollision(entity, getDx() * delta * PLAYER_SPEED, 0f))
                collidingX = true;
            if (getEntityCollider().checkCollision(entity, 0f, getDy() * delta * PLAYER_SPEED))
                collidingY = true;
        }

        if(!collidingX) setX(getX() + (getDx() * delta * PLAYER_SPEED));
        if(!collidingY) setY(getY() + (getDy() * delta * PLAYER_SPEED));
    }

    private void handleClick(int button, int x, int y) {
        if (button != MouseEvent.BUTTON1) return;

        float angle = (float) Math.atan2(y - getScreenY(), x - getScreenX());
        float dx = (float) Math.cos(angle) * Projectile.BASE_SPEED;
        float dy = (float) Math.sin(angle) * Projectile.BASE_SPEED;
        gameState.getEntityManager().spawn(new RockPellet(gameState, this, getX(), getY(), dx, dy, 16, 16));
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void onMousePress(int button, int x, int y) {
        handleClick(button, x, y);
    }

    @Override
    public void onMouseRelease(int button, int x, int y) {

    }

    @Override
    public void onMouseClick(int button, int x, int y) {
    }
}
