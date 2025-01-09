package dev.spruce.game.graphics.screen.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.screen.Screen;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIManager;
import dev.spruce.game.graphics.ui.component.impl.UIButton;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.state.impl.MainMenuState;

import java.awt.*;
import java.io.IOException;

public class PauseScreen extends Screen {

    private UIManager uiManager;

    private GameState gameState;

    public PauseScreen(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void init() {
        Game.getStateManager().togglePause();
        uiManager = new UIManager();
        uiManager.addElement(new UIButton(
                "Resume", Color.BLUE, 0, 0, 100, 80,
                ScreenSnapPoint.CENTER,
                () -> Game.getScreenManager().closeScreen()
        ));

        uiManager.addElement(new UIButton(
                "Save Game", Color.BLUE, 0, 0, 100, 80,
                ScreenSnapPoint.CENTER,
                () -> {
                    Game.getScreenManager().closeScreen();
                    try {
                        FileManager.saveGame(gameState.getName(), gameState);
                    } catch (IOException e) {
                        System.err.println("Failed to save world!");
                        throw new RuntimeException(e);
                    }
                    Game.getStateManager().setState(new MainMenuState(), true);
                }
        ));
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 100));
        graphics.fillRect(0, 0, Window.getInstance().getWidth(), Window.getInstance().getHeight());
        FontRenderer.drawStringCentred(
                graphics, "Paused.",
                Window.getInstance().getWidth() / 2,
                Window.getInstance().getHeight() / 4,
                Color.WHITE, Fonts.TITLE
        );
        uiManager.render(graphics);
    }

    @Override
    public void dispose() {
        uiManager.dispose();
        Game.getStateManager().togglePause();
    }
}
