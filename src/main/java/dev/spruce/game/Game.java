package dev.spruce.game;

import dev.spruce.game.graphics.RenderPanel;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.StateManager;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class Game {

    public static final String NAME = "Open World";
    public static final String VERSION = "alpha-0.1";
    public static final String FORMATTED_NAME = String.format("%s (%s)", NAME, VERSION);

    private RenderPanel renderPanel;
    private StateManager stateManager;

    public void start() {
        System.out.println("Starting engine!");
        InputManager.getInstance().init();
        Window.init(800, 600, FORMATTED_NAME);
        renderPanel = new RenderPanel(this);
        stateManager = new StateManager(new GameState());
        renderPanel.run();
    }

    public void update(double delta) {
        stateManager.update(delta);
    }

    public void render(Graphics graphics) {
        stateManager.render(graphics);
    }
}
