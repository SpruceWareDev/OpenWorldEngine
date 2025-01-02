package dev.spruce.game.state.impl;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.state.State;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.maps.OverworldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.io.Serializable;

public class GameState extends State implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;
    private Player player;
    private Camera camera;
    private OverworldMap map;

    private boolean firstLaunch = true;
    private boolean test = true;

    @Override
    public void init() {
        if (!firstLaunch) return;
        entityManager = new EntityManager(this);
        camera = new Camera(0, 0);
        map = new OverworldMap(this, 64, 64);
        map.generate();
        player = new Player(this, map.getSpawnX(), map.getSpawnY());
        entityManager.spawn(player);
        firstLaunch = false;
    }

    @Override
    public void update(double delta) {
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
        entityManager.dispose();
    }

    public Camera getCamera() {
        return camera;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
