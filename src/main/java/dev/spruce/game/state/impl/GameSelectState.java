package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.file.FileManager;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.UIManager;
import dev.spruce.game.graphics.ui.impl.UIButton;
import dev.spruce.game.state.State;
import dev.spruce.game.state.StateManager;

import java.awt.*;
import java.util.List;

public class GameSelectState extends State {

    private UIManager uiManager;

    @Override
    public void init() {
        uiManager = new UIManager();
        List<String> games = FileManager.getGameNames();
        for (String game : games) {
            uiManager.addElement(new UIButton(
                    game,
                    Color.BLUE,
                    0,
                    0,
                    100,
                    80,
                    ScreenSnapPoint.CENTER,
                    () -> {
                        try {
                            Game.getStateManager().setState(FileManager.loadGame(game), false);
                        } catch (Exception e) {
                            System.err.println("Failed to load game: " + game);
                            e.printStackTrace();
                        }
                    }
            ));
        }
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, "Select game to load...",
                Window.getInstance().getWidth() / 2,
                Window.getInstance().getHeight() / 4,
                Color.WHITE, Fonts.TITLE
        );
        uiManager.render(graphics);
    }

    @Override
    public void dispose() {

    }
}
