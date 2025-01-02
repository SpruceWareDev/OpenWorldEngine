package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.ui.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.UIManager;
import dev.spruce.game.graphics.ui.impl.UIButton;
import dev.spruce.game.state.State;
import dev.spruce.game.state.StateManager;

import java.awt.*;

public class MainMenuState extends State {

    private UIManager uiManager;

    @Override
    public void init() {
        uiManager = new UIManager();
        uiManager.addElement(new UIButton(
                "New Game",
                Color.BLUE,
                Window.getInstance().getWidth() / 2 - 100,
                Window.getInstance().getHeight() / 2 - 50,
                100,
                80,
                ScreenSnapPoint.CENTER,
                () -> Game.getStateManager().setState(new GameState(), true)
        ));

        uiManager.addElement(new UIButton(
                "Load Game",
                Color.BLUE,
                Window.getInstance().getWidth() / 2 - 100,
                Window.getInstance().getHeight() / 2 - 50,
                100,
                80,
                ScreenSnapPoint.CENTER,
                () -> System.out.println("Ligma balls!")
        ));
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, "Balls :3",
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
