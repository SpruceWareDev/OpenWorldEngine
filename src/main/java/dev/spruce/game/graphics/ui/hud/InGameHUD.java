package dev.spruce.game.graphics.ui.hud;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.particle.ParticleRenderer;
import dev.spruce.game.graphics.ui.hud.effect.MovingLetterEffect;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.util.DifficultyUtils;
import dev.spruce.game.util.RenderUtils;
import dev.spruce.game.util.TimerUtils;

import java.awt.*;

public class InGameHUD {

    private final GameState gameState;

    private static final int ITEM_SLOT_SIZE = 56;
    private static final int ITEM_SLOT_PADDING = 4;

    private MovingLetterEffect difficultyBarEffect;

    public InGameHUD(GameState gameState) {
        this.gameState = gameState;
        init();
    }

    private void init() {
        this.difficultyBarEffect = new MovingLetterEffect(0, 0, 260, 160, '+', new Color(0x8100FFA6, true), 1.0f);
    }

    public void update(double delta) {
        this.difficultyBarEffect.update(delta);
    }

    public void render(Graphics graphics) {
        int screenW = Window.getInstance().getWidth();
        int screenH = Window.getInstance().getHeight();

        FontRenderer.drawString(graphics, Game.FORMATTED_NAME, 10, 10, false, Color.white, Fonts.DEFAULT);
        renderHotbar(graphics, screenW, screenH);
        renderTimeDifficulty(graphics, screenW, screenH);
        renderDebugInfo(graphics);
    }

    private void renderHotbar(Graphics graphics, int screenW, int screenH) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int slots = gameState.getPlayer().getInventory().getCapacity();
        float hotbarWidth = (slots * (ITEM_SLOT_SIZE + ITEM_SLOT_PADDING));

        float hotbarX = (screenW / 2f) - (hotbarWidth / 2);
        float hotbarY = screenH - (ITEM_SLOT_SIZE + 54);

        for (int i = 0; i < slots; i++) {
            float x = hotbarX + (i * (ITEM_SLOT_SIZE + ITEM_SLOT_PADDING));
            RenderUtils.drawRect(graphics, (int) x, (int) hotbarY, ITEM_SLOT_SIZE, ITEM_SLOT_SIZE, new Color(0,0,0,128));
            // Highlight selected slot if not using spells
            if (!gameState.getPlayer().isUsingSpells()) {
                int slot = gameState.getPlayer().getSelectedSlot();
                if (i == slot) {
                    RenderUtils.drawRect(graphics, (int) x - 1, (int) hotbarY - 1, ITEM_SLOT_SIZE + 2, ITEM_SLOT_SIZE + 2, new Color(255, 255, 255, 128));
                }
            }

            if (!gameState.getPlayer().getInventory().isSlotEmpty(i)) {
                ItemStack stack = gameState.getPlayer().getInventory().getSlot(i);
                // Draw item image
                graphics2D.drawImage(
                        Assets.getInstance().getItemTextures().getAsset(stack.getItem().getName()),
                        (int) x, (int) hotbarY, ITEM_SLOT_SIZE, ITEM_SLOT_SIZE, null
                );
                // Draw stack amount
                FontRenderer.drawString(
                        graphics, String.valueOf(stack.getQuantity()),
                        (int) (x + ITEM_SLOT_SIZE - 24), (int) (hotbarY + ITEM_SLOT_SIZE - 24),
                        false, Color.white, Fonts.DEFAULT
                );
            }
        }

        // Render health
        float healthWidth = ((hotbarWidth / 2f) * ((float) gameState.getPlayer().getHealth() / gameState.getPlayer().getMaxHealth())) - ITEM_SLOT_PADDING;
        RenderUtils.drawRect(graphics, hotbarX, hotbarY - 8, healthWidth, 4, Color.RED);

        // Render mana
        float manaWidth = (
                (hotbarWidth / 2f) * (
                        (float) gameState.getPlayer().getManaManager().getMana() / gameState.getPlayer().getManaManager().getMaxMana()
                ))
                - ITEM_SLOT_PADDING;
        RenderUtils.drawRect(graphics, hotbarX + (hotbarWidth / 2f), hotbarY - 8, manaWidth, 4, Color.BLUE);
    }

    private void renderDebugInfo(Graphics graphics) {
        if (Game.debug) {
            FontRenderer.drawString(graphics,
                    "Entity Count: " + gameState.getEntityManager().getEntities().size(),
                    10, 30, false, Color.white, Fonts.DEFAULT
            );
            FontRenderer.drawString(graphics,
                    "Active audio threads: " + SoundManager.getInstance().getActiveAudioThreads(),
                    10, 50, false, Color.white, Fonts.DEFAULT
            );
            FontRenderer.drawString(graphics,
                    "Particle Count: " + gameState.getParticleRenderer().getParticleCount(),
                    10, 70, false, Color.white, Fonts.DEFAULT
            );
            FontRenderer.drawString(graphics,
                    "Seconds Alive: " + gameState.getSecondsAlive(),
                    10, 90, false, Color.white, Fonts.DEFAULT
            );
        }
        if (Game.devSpawnMode) {
            FontRenderer.drawStringCentred(
                    graphics, "Dev Spawn Mode",
                    Window.getInstance().getWidth() / 2, 30,
                    Color.YELLOW, Fonts.LARGE
            );
        }
    }

    private void renderTimeDifficulty(Graphics graphics, int screenW, int screenH) {
        Color backgroundColor;
        if (gameState.getDifficulty() < DifficultyUtils.DIFFICULTY_NAMES.length - 1) {
            int redLevel = (int) (120 * (gameState.getDifficulty() / (float) (DifficultyUtils.DIFFICULTY_NAMES.length - 1)));
            backgroundColor = new Color(redLevel, 0, 0, 128);
        } else {
            backgroundColor = new Color(120, 0, 0, 128);
        }

        int backgroundX = screenW - 270;
        int backgroundY = 20;

        graphics.setColor(backgroundColor);
        graphics.fillRect(backgroundX, backgroundY, 260, 160);

        FontRenderer.drawString(
                graphics, "Difficulty", screenW - 260, 22,
                false, Color.white, Fonts.LARGE
        );
        FontRenderer.drawString(
                graphics, "Time: " + TimerUtils.formatTime(gameState.getTicksAlive()) + "s",
                screenW - 260, 50, false, Color.white, Fonts.DEFAULT
        );
        FontRenderer.drawString(
                graphics, "Kills: " + gameState.getKills(),
                screenW - 260, 70, false, Color.white, Fonts.DEFAULT
        );
        FontRenderer.drawString(
                graphics, "Difficulty: " + DifficultyUtils.getDifficultyName(gameState.getDifficulty()),
                screenW - 260, 90, false, Color.white, Fonts.DEFAULT
        );

        this.difficultyBarEffect.setX(backgroundX);
        this.difficultyBarEffect.setY(backgroundY);

        graphics.setColor(Color.white);
        graphics.drawRect(backgroundX + 20, backgroundY + 110, 220, 40);
        RenderUtils.scissorStart(graphics, backgroundX + 22, backgroundY + 112, 216, 36);
        this.difficultyBarEffect.render(graphics);
        RenderUtils.scissorEnd(graphics);

        FontRenderer.drawStringCentred(graphics,
                DifficultyUtils.getDifficultyName(gameState.getDifficulty()),
                backgroundX + 130, backgroundY + 105,
                Color.white, Fonts.LARGE
        );
    }
}
