package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.screen.ScreenManager;
import dev.spruce.game.graphics.screen.impl.PauseScreen;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.State;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.maps.OverworldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.io.Serializable;

public class GameState extends State implements Serializable, IKeyInput {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private EntityManager entityManager;
    private Player player;
    private Camera camera;
    private OverworldMap map;

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
    }

    @Override
    public void update(double delta) {
        // TODO: Make this less ass
        if (!InputManager.getInstance().isSubscribed(this)) {
            InputManager.getInstance().subscribe(this);
        }
        entityManager.update(delta);
    }

    @Override
    public void render(Graphics graphics) {
        camera.centerOn(player);
        map.render(graphics, camera);
        entityManager.render(graphics, camera);
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
}
