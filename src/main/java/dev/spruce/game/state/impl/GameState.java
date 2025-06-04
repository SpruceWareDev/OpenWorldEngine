package dev.spruce.game.state.impl;

import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.particle.ParticleRenderer;
import dev.spruce.game.graphics.ui.hud.InGameHUD;
import dev.spruce.game.input.IKeyInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.State;
import dev.spruce.game.util.MathUtils;
import dev.spruce.game.util.Spawner;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.entity.DamageableEntity;
import dev.spruce.game.world.entity.Entity;
import dev.spruce.game.world.entity.EntityManager;
import dev.spruce.game.world.entity.Interactable;
import dev.spruce.game.world.entity.impl.Player;
import dev.spruce.game.world.entity.impl.hostile.HostileEntity;
import dev.spruce.game.world.entity.impl.hostile.TestEnemy;
import dev.spruce.game.world.entity.impl.projectile.Projectile;
import dev.spruce.game.world.maps.TestingMap;

import java.io.IOException;
import java.util.List;

public class GameState extends State implements IKeyInput {

    private final String name;
    private int seed;

    private EntityManager entityManager;
    private Player player;
    private Camera camera;
    private Map map;

    private InGameHUD inGameHUD;
    private ParticleRenderer particleRenderer;

    private Spawner spawner;
    private long ticksAlive = 0;
    private int kills = 0;
    private int difficulty = 0;

    public GameState(String name, int seed) {
        this.name = name;
        this.seed = seed;
    }

    @Override
    public void init() {
        InputManager.getInstance().subscribeKey(this);
        worldInit();
        inGameHUD = new InGameHUD(this);
        camera.centerOn(player, false);
        particleRenderer = new ParticleRenderer();
        spawner = new Spawner(this);
    }

    /**
     * Initializes the world by creating the entity manager, camera, map, and player.
     * This method is called during the initialization of the game state.
     */
    private void worldInit() {
        entityManager = new EntityManager(this);
        camera = new Camera(0, 0);
        map = new TestingMap();
        map.generate(this);
        player = new Player(map.getSpawnX(), map.getSpawnY());
        entityManager.spawn(player);
    }

    @Override
    public void update(double delta) {
        Game.getProfiler().startProfile("game_tick");
        ticksAlive++;
        handleDifficulty();
        spawner.update();
        camera.update(delta);
        entityManager.update(delta);
        checkProjectileCollisions();
        inGameHUD.update(delta);
        particleRenderer.update(delta);
        Game.getProfiler().endProfile("game_tick");
    }

    /**
     * Increases the difficulty every 60 seconds.
     * This will be replaced with a more complex system later.
     */
    private void handleDifficulty() {
        if (ticksAlive % (RenderPanel.FPS_TARGET * (60L * (difficulty + 1))) == 0) {
            difficulty++;
        }
    }

    /**
     * Checks for collisions between projectiles and entities.
     * If a projectile collides with a damageable entity, it deals damage and despawns the projectile.
     */
    private void checkProjectileCollisions() {
        for (Entity entity : entityManager.getOnScreenEntities()) {
            if (!(entity instanceof Projectile projectile))
                continue;

            for (Entity collidingEntity : entityManager.getEntities()) {
                if (collidingEntity instanceof HostileEntity && projectile.getOwner() instanceof HostileEntity)
                    continue;
                if (collidingEntity.equals(entity) || projectile.getOwner().equals(collidingEntity))
                    continue;
                if (!(collidingEntity instanceof DamageableEntity damageableEntity))
                    continue;

                if (collidingEntity.getEntityCollider().isPointColliding(projectile.getX(), projectile.getY())) {
                    entityManager.despawn(projectile);
                    damageableEntity.dealDamage(projectile.getDamage());

                    if (projectile.isOnFire()) {
                        damageableEntity.setOnFire(true);
                    }
                }
            }
        }
    }

    @Override
    public void render() {
        camera.centerOn(player, true);
        Game.getProfiler().startProfile("map");
        map.render(camera);
        Game.getProfiler().endProfile("map");
        Game.getProfiler().startProfile("entities");
        entityManager.render(camera);
        Game.getProfiler().endProfile("entities");
        Game.getProfiler().startProfile("particles");
        particleRenderer.render(camera);
        Game.getProfiler().endProfile("particles");
        inGameHUD.render();
    }

    @Override
    public void onKeyPress(int keyCode) {
        if (keyCode == Raylib.KEY_E) {
            for (Entity entity : entityManager.getOnScreenEntities()) {
                if (entity instanceof Interactable interactable) {
                    if (MathUtils.isWithinDistance(player, entity, Player.INTERACT_DISTANCE)) {
                        interactable.interact();
                    }
                }
            }
        }
    }

    @Override
    public void onKeyRelease(int keyCode) {
        /*
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE -> {
                if (Game.getScreenManager().isScreenOpen()) {
                    Game.getScreenManager().closeScreen();
                } else {
                    Game.getScreenManager().setScreen(new PauseScreen(this), true);
                }
            }
            case KeyEvent.VK_E -> Game.getScreenManager().setScreen(new SpellSelectionScreen(), true);
        }
         */
    }

    @Override
    public void onKeyTyped(int keyCode, char keyChar) {

    }

    /*
    @Override
    public void onMousePress(int button, int x, int y) {
        if (Game.getStateManager().isPaused()) return;

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

                if (canInteract && !player.isUsingSpells()) {
                    ((Interactable) entity).interact();
                }
            }
        } else if (button == 3) {
            if (Game.devSpawnMode) {
                TestEnemy testEnemy = new TestEnemy(x + camera.getX(), y + camera.getY());
                entityManager.spawn(testEnemy);
            }
        }
    }

     */

    public void addKill() {
        kills++;
    }

    @Override
    public void dispose() {
        //entityManager.dispose();
    }

    public int getKills() {
        return kills;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getSecondsAlive() {
        return (int) (ticksAlive / RenderPanel.FPS_TARGET);
    }

    public long getTicksAlive() {
        return ticksAlive;
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

    public ParticleRenderer getParticleRenderer() {
        return particleRenderer;
    }
}
