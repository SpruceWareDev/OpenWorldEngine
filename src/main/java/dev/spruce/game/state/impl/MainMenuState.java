package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIManager;
import dev.spruce.game.graphics.ui.component.impl.UIButton;
import dev.spruce.game.state.State;

import java.awt.*;

public class MainMenuState extends State {

    private UIManager uiManager;

    @Override
    public void init() {
        uiManager = new UIManager();
        uiManager.addElement(new UIButton(
                "New Game", Color.BLUE, 0, 0, 150, 50,
                ScreenSnapPoint.CENTER,
                () -> Game.getStateManager().setState(new WorldCreateState(), true)
        ));

        uiManager.addElement(new UIButton(
                "Load Game", Color.BLUE, 0, 0, 150, 50,
                ScreenSnapPoint.CENTER,
                () -> Game.getStateManager().setState(new GameSelectState(), true)
        ));

        uiManager.addElement(new UIButton(
                "Quit", Color.BLUE, 0, 0, 150, 50,
                ScreenSnapPoint.CENTER,
                () -> System.exit(0)
        ));
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, Game.FORMATTED_NAME,
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
