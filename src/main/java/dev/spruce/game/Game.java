package dev.spruce.game;

import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.StateManager;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.state.impl.MainMenuState;

import java.awt.*;
import java.io.IOException;

public class Game {

    public static final String NAME = "Open World";
    public static final String VERSION = "alpha-0.1";
    public static final String FORMATTED_NAME = String.format("%s (%s)", NAME, VERSION);

    private RenderPanel renderPanel;
    private StateManager stateManager;

    private GameState currentGame;

    public void start() {
        System.out.println("Starting engine!");
        InputManager.getInstance().init();
        try {
            FileManager.checkDirectories();
        } catch (IOException e) {
            System.err.println("Failed to create or check game data directories!");
            throw new RuntimeException(e);
        }
        Window.init(800, 600, FORMATTED_NAME);
        renderPanel = new RenderPanel(this);

        /*
        try {
            currentGame = FileManager.loadGame("nutsack");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //currentGame = new GameState();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileManager.saveGame("nutsack", currentGame);
            } catch (IOException e) {
                System.err.println("It brokie! >:3");
                throw new RuntimeException(e);
            }
        }));

         */

        stateManager = new StateManager(new MainMenuState());
        renderPanel.run();
    }

    public void update(double delta) {
        stateManager.update(delta);
    }

    public void render(Graphics graphics) {
        stateManager.render(graphics);
    }
}
