package dev.spruce.game;

import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.screen.ScreenManager;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.state.StateManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.state.impl.MainMenuState;

import java.awt.*;
import java.io.IOException;

public class Game {

    public static final String NAME = "Open World";
    public static final String VERSION = "alpha-0.1";
    public static final String FORMATTED_NAME = String.format("%s (%s)", NAME, VERSION);

    public static boolean debug = false;

    private RenderPanel renderPanel;
    private static StateManager stateManager;
    private static ScreenManager screenManager;

    public void start() {
        System.out.println("Starting engine!");
        InputManager.getInstance().init();
        try {
            FileManager.checkDirectories();
        } catch (IOException e) {
            System.err.println("Failed to create or check game data directories!");
            throw new RuntimeException(e);
        }
        SoundManager.init();
        Window.init(800, 600, FORMATTED_NAME);
        renderPanel = new RenderPanel(this);

        stateManager = new StateManager(new MainMenuState());
        screenManager = new ScreenManager();
        renderPanel.run();
    }

    public void update(double delta) {
        SoundManager.getInstance().update();
        stateManager.update(delta);
        screenManager.update(delta);
    }

    public void render(Graphics graphics) {
        stateManager.render(graphics);
        screenManager.render(graphics);
    }

    public static StateManager getStateManager() {
        return stateManager;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }
}
