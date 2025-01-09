package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.screen.ScreenManager;
import dev.spruce.game.graphics.screen.impl.PauseScreen;
import dev.spruce.game.graphics.ui.hud.InGameHUD;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.State;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.maps.OverworldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.io.Serializable;

public class GameState extends State implements Serializable, IKeyInput, IMouseInput {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private EntityManager entityManager;
    private Player player;
    private Camera camera;
    private OverworldMap map;

    private InGameHUD inGameHUD;

    public GameState(String name) {
        this.name = name;
    }

    @Override
    public void init() {
        entityManager = new EntityManager(this);
        camera = new Camera(0, 0);
        map = new OverworldMap(this, 1024, 1024);
        map.generate();
        player = new Player(this, map.getSpawnX(), map.getSpawnY());
        entityManager.spawn(player);
        inGameHUD = new InGameHUD(this);
    }

    @Override
    public void update(double delta) {
        // TODO: Make this less ass
        if (!InputManager.getInstance().isSubscribedKey(this)) {
            InputManager.getInstance().subscribeKey(this);
        }
        if (!InputManager.getInstance().isSubscribedMouse(this)) {
            InputManager.getInstance().subscribeMouse(this);
        }

        entityManager.update(delta);
        inGameHUD.update(delta);
    }

    @Override
    public void render(Graphics graphics) {
        camera.centerOn(player);
        map.render(graphics, camera);
        entityManager.render(graphics, camera);
        inGameHUD.render(graphics);
    }

    @Override
    public void dispose() {
        //entityManager.dispose();
    }

    public Camera getCamera() {
        return camera;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE -> Game.getScreenManager().setScreen(new PauseScreen(this));
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
        this.player.handleClick(button, x, y);
    }

    @Override
    public void onMouseRelease(int button, int x, int y) {

    }

    @Override
    public void onMouseClick(int button, int x, int y) {

    }
}
