package dev.spruce.game.world.entity.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.impl.DeathState;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.entity.DamageableEntity;
import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.magic.ManaManager;
import dev.spruce.game.world.magic.spell.SpellManager;
import dev.spruce.game.world.magic.spell.impl.PlasmaShotSpell;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class Player extends DamageableEntity {

    public static final float PLAYER_SPEED = 200f;
    public static final int INTERACT_DISTANCE = 125;

    private final ManaManager manaManager;
    private final SpellManager spellManager;

    //private final int ANIMATION_DELAY_TICKS = 10;
    //private int animationTicks = 0;
    //private int spriteIndex = 0;

    public Player(float x, float y) {
        super(x, y, 32, 32, 100);
        this.manaManager = new ManaManager(10);
        this.spellManager = new SpellManager();
        this.spellManager.addSpell(new PlasmaShotSpell());
    }

    @Override
    public void update(double delta) {
        manaManager.update();
        move((float) delta);

        /*
        if (animationTicks >= ANIMATION_DELAY_TICKS) {
            if (spriteIndex + 1 >= Assets.getInstance().getEntityTextures().getAsset("player_idle").getImages().size()) {
                spriteIndex = 0;
            } else {
                spriteIndex++;
            }
            animationTicks = 0;
        }
        animationTicks++;

         */
    }

    private void move(float delta) {
        GameState gs = Game.getStateManager().getGameState().get();
        resetVelocity();
        if (Raylib.IsKeyDown(Raylib.KEY_W)) {
            setDy(-1);
        } else if (Raylib.IsKeyDown(Raylib.KEY_S)) {
            setDy(1);
        }
        if (Raylib.IsKeyDown(Raylib.KEY_A)) {
            setDx(-1);
        } else if (Raylib.IsKeyDown(Raylib.KEY_D)) {
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
        float angle = (float) Math.atan2(y - getScreenY(camera), x - getScreenX(camera));
        spellManager.castCurrentSpell(manaManager, getX(), getY(), angle);
    }

    @Override
    public void render(Camera camera) {
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

    public ManaManager getManaManager() {
        return manaManager;
    }

    public SpellManager getSpellManager() {
        return spellManager;
    }
}
