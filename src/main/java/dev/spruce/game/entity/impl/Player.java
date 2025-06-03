package dev.spruce.game.entity.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.assets.AssetManager;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.assets.managers.EntityTextureManager;
import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.item.Inventory;
import dev.spruce.game.magic.ManaManager;
import dev.spruce.game.magic.spell.SpellManager;
import dev.spruce.game.magic.spell.impl.PlasmaShotSpell;
import dev.spruce.game.state.impl.DeathState;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class Player extends DamageableEntity {

    public static final float PLAYER_SPEED = 5f;
    public static final int INTERACT_DISTANCE = 125;

    private final ManaManager manaManager;
    private final SpellManager spellManager;

    private final Inventory inventory;
    private int selectedSlot = 0;
    private boolean usingSpells = false;

    private final int ANIMATION_DELAY_TICKS = 10;
    private int animationTicks = 0;
    private int spriteIndex = 0;

    public Player(float x, float y) {
        super(x, y, 32, 32, 100);
        this.inventory = new Inventory(8);
        this.manaManager = new ManaManager(10);
        this.spellManager = new SpellManager();
        this.spellManager.addSpell(new PlasmaShotSpell());
    }

    @Override
    public void update(double delta) {
        manaManager.update();
        move((float) delta);

        if (animationTicks >= ANIMATION_DELAY_TICKS) {
            if (spriteIndex + 1 >= Assets.getInstance().getEntityTextures().getAsset("player_idle").getImages().size()) {
                spriteIndex = 0;
            } else {
                spriteIndex++;
            }
            animationTicks = 0;
        }
        animationTicks++;
    }

    private void move(float delta) {
        GameState gs = Game.getStateManager().getGameState().get();
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

        // Stop player from getting stuck inside other entities if their colliders overlap
        if (collidingX && collidingY) {
            // If colliding in both directions, prioritize Y direction to avoid getting stuck
            collidingX = false;
        }

        applyVelocity(delta, PLAYER_SPEED, collidingX, collidingY);
    }

    public void handleClick(Camera camera, int button, int x, int y) {
        if (button != MouseEvent.BUTTON1) return;
        if (usingSpells) {
            float angle = (float) Math.atan2(y - getScreenY(camera), x - getScreenX(camera));
            spellManager.castCurrentSpell(manaManager, getX(), getY(), angle);
        }
    }

    public void handleKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_1 -> selectedSlot = 0;
            case KeyEvent.VK_2 -> selectedSlot = 1;
            case KeyEvent.VK_3 -> selectedSlot = 2;
            case KeyEvent.VK_4 -> selectedSlot = 3;
            case KeyEvent.VK_5 -> selectedSlot = 4;
            case KeyEvent.VK_6 -> selectedSlot = 5;
            case KeyEvent.VK_7 -> selectedSlot = 6;
            case KeyEvent.VK_8 -> selectedSlot = 7;
            case KeyEvent.VK_R -> usingSpells = !usingSpells;
        }
    }

    @Override
    public void render(Camera camera) {
        //graphics.setColor(Color.BLUE);
        //graphics.fillRect((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());

        /*
        BufferedImage texture = Assets.getInstance().getEntityTextures().getAsset("player_idle").getImages().get(spriteIndex);
        g2d.drawImage(texture,
                (int) ((getX() - camera.getX()) - (getWidth() * 10) / 2),
                (int) ((getY() - camera.getY()) - (getWidth() * 10) / 2),
                (int) getWidth() * 10, (int) getHeight() * 10, null);

         */

        int x = (int) (getX() - camera.getX());
        int y = (int) (getY() - camera.getY());
        Raylib.DrawRectangle(x, y, (int) getWidth(), (int) getHeight(), Colors.RED);
    }

    @Override
    public void onDeath() {
        if (Game.devInvincibility)
            return;
        DeathState deathState = new DeathState(Game.getStateManager().getGameState().get());
        Game.getStateManager().getGameState().get().dispose();
        Game.getStateManager().setState(deathState, true);
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

    public boolean isUsingSpells() {
        return usingSpells;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }
}
