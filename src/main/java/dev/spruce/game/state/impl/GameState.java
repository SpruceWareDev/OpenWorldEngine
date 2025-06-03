package dev.spruce.game.state.impl;

import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.entity.DamageableEntity;
import dev.spruce.game.entity.Entity;
import dev.spruce.game.entity.EntityManager;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.entity.impl.hostile.HostileEntity;
import dev.spruce.game.entity.impl.hostile.TestEnemy;
import dev.spruce.game.entity.impl.projectile.Projectile;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.particle.ParticleRenderer;
import dev.spruce.game.graphics.ui.hud.InGameHUD;
import dev.spruce.game.state.State;
import dev.spruce.game.util.Spawner;
import dev.spruce.game.world.Map;
import dev.spruce.game.world.maps.TestingMap;

import java.io.IOException;
import java.util.List;

public class GameState extends State {

    private final String name;
    private final boolean newGame;
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
            newGameInit();
        } else {
            try {
                loadInit();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to load game state: " + e.getMessage());
                Game.getStateManager().setState(new MainMenuState(), true);
            }
        }
        inGameHUD = new InGameHUD(this);
        camera.centerOn(player, false);
        particleRenderer = new ParticleRenderer();
        spawner = new Spawner(this);
    }

    // Only called when a new game is started
    private void newGameInit() {
        map = new TestingMap();
        //map = new OverworldMap(256, 256, seed);
        map.generate(this);
        player = new Player(map.getSpawnX(), map.getSpawnY());
        entityManager.spawn(player);

        // TODO: Remove this bc its to test entities
        TestEnemy testEnemy = new TestEnemy(map.getSpawnX() + 30, map.getSpawnY() + 30);
        entityManager.spawn(testEnemy);
    }

    // Only called when the game is loaded from a save
    private void loadInit() throws IOException, ClassNotFoundException {
        map = FileManager.loadMap(name);
        seed = map.getSeed();

        List<Entity> loadedEntities;
        loadedEntities = FileManager.loadEntities(name);

        for (Entity e : loadedEntities) {
            if (e instanceof Player) {
                player = (Player) e;
            }
            entityManager.spawn(e);
        }
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

    private void handleDifficulty() {
        if (ticksAlive % (RenderPanel.FPS_TARGET * (60L * (difficulty + 1))) == 0) {
            difficulty++;
        }
    }

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
        camera.centerOn(player, false);

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

    /*
    @Override
    public void onKeyPress(int keyCode) {
        player.handleKey(keyCode);
    }

    @Override
    public void onKeyRelease(int keyCode) {
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
    }

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
