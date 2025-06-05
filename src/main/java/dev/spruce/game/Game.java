package dev.spruce.game;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.screen.ScreenManager;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.state.StateManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.state.impl.MainMenuState;
import dev.spruce.game.util.Profiler;

import java.io.IOException;

public class Game {

    // Game metadata
    public static final String NAME = "Rogue Wizards";
    public static final String VERSION = "0.2";
    public static final BuildVersion BUILD_VERSION = BuildVersion.DEVELOPMENT;
    public static final String FORMATTED_NAME = String.format("%s (%s) (%s)", NAME, VERSION, BUILD_VERSION.name);

    // Development flags
    public static boolean debug = false;
    public static boolean devInvincibility = false;

    // Game components
    private RenderPanel renderPanel;
    private static StateManager stateManager;
    private static ScreenManager screenManager;

    // Development tools
    private static Profiler profiler;

    /**
     * Starts the game engine.
     * Initializes all necessary components and starts the main game loop.
     */
    public void start() {
        System.out.println("Starting engine!");
        System.out.println(FORMATTED_NAME);

        profiler = new Profiler();
        profiler.init();

        System.out.println("Initializing file manager...");
        try {
            FileManager.checkDirectories();
        } catch (IOException e) {
            System.err.println("Failed to create or check game data directories!");
            throw new RuntimeException(e);
        }
        System.out.println("File manager initialized.");

        InputManager.getInstance().init();

        System.out.println("Initializing sound manager...");
        SoundManager.init();
        System.out.println("Sound manager initialized.");

        System.out.println("Initializing render panel...");
        renderPanel = new RenderPanel(this, FORMATTED_NAME, 1280, 720);
        System.out.println("Render panel initialized.");

        Assets.getInstance();

        System.out.println("Starting renderer...");
        stateManager = new StateManager(new MainMenuState());
        screenManager = new ScreenManager();
        renderPanel.run();
    }

    /**
     * Updates the game state and renders the current frame.
     *
     * @param delta Time since the last update in seconds.
     */
    public void update(double delta) {
        SoundManager.getInstance().update();
        InputManager.getInstance().pollInputs();
        stateManager.update(delta);
        screenManager.update(delta);
    }

    public void render() {
        stateManager.render();
        screenManager.render();
    }

    public void dispose() {
        Assets.getInstance().dispose();
    }

    public static StateManager getStateManager() {
        return stateManager;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }

    public static Profiler getProfiler() {
        return profiler;
    }
}
