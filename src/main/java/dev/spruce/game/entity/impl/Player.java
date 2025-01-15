package dev.spruce.game.entity.impl;

import dev.spruce.game.Game;
import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.entity.impl.projectile.RockPellet;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.item.Inventory;
import dev.spruce.game.magic.ManaManager;
import dev.spruce.game.magic.spell.SpellManager;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class Player extends DamageableEntity {

    public static final float PLAYER_SPEED = 5f;
    public static final int INTERACT_DISTANCE = 125;

    private final ManaManager manaManager;
    private final SpellManager spellManager;
    private final Inventory inventory;

    public Player(float x, float y) {
        super(x, y, 32, 32, 100);
        this.inventory = new Inventory(8);
        this.manaManager = new ManaManager(10);
        this.spellManager = new SpellManager();
    }

    @Override
    public void update(double delta) {
        move((float) delta);
    }

    private void move(float delta) {
        GameState gs = Game.getStateManager().getGameState();
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
        List<Entity> onScreenEntities = gs.getEntityManager().getOnScreenEntities();

        for (Entity entity : onScreenEntities) {
            if (getEntityCollider().checkCollision(entity, getDx() * delta * PLAYER_SPEED, 0f))
                collidingX = true;
            if (getEntityCollider().checkCollision(entity, 0f, getDy() * delta * PLAYER_SPEED))
                collidingY = true;
        }

        if(!collidingX) setX(getX() + (getDx() * delta * PLAYER_SPEED));
        if(!collidingY) setY(getY() + (getDy() * delta * PLAYER_SPEED));
    }

    public void handleClick(Camera camera, int button, int x, int y) {
        GameState gs = Game.getStateManager().getGameState();
        if (button != MouseEvent.BUTTON1) return;

        float angle = (float) Math.atan2(y - getScreenY(camera), x - getScreenX(camera));
        spellManager.castCurrentSpell(manaManager, getX(), getY(), angle);
    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void onDeath() {
        System.out.println("Player died! omg");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ManaManager getManaManager() {
        return manaManager;
    }

    public SpellManager getSpellManager() {
        return spellManager;
    }
}
