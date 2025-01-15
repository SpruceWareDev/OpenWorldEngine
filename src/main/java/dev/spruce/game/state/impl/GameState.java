package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;
import dev.spruce.game.entity.Interactable;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.screen.impl.PauseScreen;
import dev.spruce.game.graphics.ui.hud.InGameHUD;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.item.Items;
import dev.spruce.game.state.State;
import dev.spruce.game.util.MathUtils;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.maps.OverworldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;
import java.util.List;

public class GameState extends State implements IKeyInput, IMouseInput {

    private final String name;
    private final boolean newGame;
    private int seed;

    private EntityManager entityManager;
    private Player player;
    private Camera camera;
    private Map map;

    private InGameHUD inGameHUD;

    public GameState(String name, boolean newGame, int seed) {
        this.name = name;
        this.newGame = newGame;
        this.seed = seed;
    }

    @Override
    public void init() {
        entityManager = new EntityManager(this);
        camera = new Camera(0, 0);
        if (newGame) {
            map = new OverworldMap(1024, 1024, seed);
            map.generate(this);
            player = new Player(map.getSpawnX(), map.getSpawnY());
            entityManager.spawn(player);
        } else {
            try {
                map = FileManager.loadMap(name);
                seed = map.getSeed();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            List<Entity> loadedEntities;
            try {
                loadedEntities = FileManager.loadEntities(name);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (Entity e : loadedEntities) {
                if (e instanceof Player) {
                    player = (Player) e;
                }
                entityManager.spawn(e);
            }
        }
        inGameHUD = new InGameHUD(this);
        InputManager.getInstance().subscribeMouse(this);
        InputManager.getInstance().subscribeKey(this);
        camera.centerOn(player, false);
    }

    @Override
    public void update(double delta) {
        camera.update(delta);
        entityManager.update(delta);
        inGameHUD.update(delta);
    }

    @Override
    public void render(Graphics graphics) {
        camera.centerOn(player, true);
        map.render(graphics, camera);
        entityManager.render(graphics, camera);
        inGameHUD.render(graphics);
    }

    @Override
    public void onKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE -> Game.getScreenManager().setScreen(new PauseScreen(this), true);
        }
    }

    @Override
    public void onKeyRelease(int keyCode) {

    }

    @Override
    public void onKeyTyped(int keyCode, char keyChar) {

    }

    @Override
    public void onMousePress(int button, int x, int y) {
        this.player.handleClick(camera, button, x, y);

        // Entity interactions
        if (button == 1) {
            for (Entity entity : entityManager.getOnScreenEntities()) {
                if (entity instanceof Player || !(entity instanceof Interactable))
                    continue;

                float screenX = entity.getScreenX(camera);
                float screenY = entity.getScreenY(camera);
                float colliderX = (float) entity.getEntityCollider().getBounds().getX();
                float colliderY = (float) entity.getEntityCollider().getBounds().getY();
                float colliderW = (float) entity.getEntityCollider().getBounds().getWidth();
                float colliderH = (float) entity.getEntityCollider().getBounds().getHeight();
                boolean canInteract =
                        InputManager.getInstance().isMouseOver(
                                (int) (screenX + colliderX), (int) (screenY + colliderY), (int) colliderW, (int) colliderH
                        ) && MathUtils.isWithinDistance(player, entity, Player.INTERACT_DISTANCE);

                if (canInteract) {
                    ((Interactable) entity).interact();
                }
            }
        }
    }

    @Override
    public void onMouseRelease(int button, int x, int y) {

    }

    @Override
    public void onMouseClick(int button, int x, int y) {

    }

    @Override
    public void dispose() {
        //entityManager.dispose();
    }

    public  Camera getCamera() {
        return camera;
    }

    public  EntityManager getEntityManager() {
        return entityManager;
    }

    public String getName() {
        return name;
    }

    public  Player getPlayer() {
        return player;
    }

    public  Map getMap() {
        return map;
    }

    public int getSeed() {
        return seed;
    }
}
