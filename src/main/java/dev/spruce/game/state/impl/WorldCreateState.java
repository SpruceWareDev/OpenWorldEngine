package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.UIManager;
import dev.spruce.game.graphics.ui.impl.UIButton;
import dev.spruce.game.graphics.ui.impl.UITextBox;
import dev.spruce.game.state.State;

import java.awt.*;

public class WorldCreateState extends State {

    private UIManager uiManager;
    private UITextBox worldName;

    @Override
    public void init() {
        uiManager = new UIManager();
        worldName = new UITextBox(
                0, 0, 200, 40,
                ScreenSnapPoint.CENTER
        );
        uiManager.addElement(worldName);
        uiManager.addElement(new UIButton(
                "Create",
                Color.BLUE,
                0, 0, 200, 80,
                ScreenSnapPoint.CENTER,
                () -> Game.getStateManager().setState(new GameState(worldName.getText()), true)
        ));
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, "New Game.",
                dev.spruce.game.graphics.Window.getInstance().getWidth() / 2,
                Window.getInstance().getHeight() / 4,
                Color.WHITE, Fonts.TITLE
        );
        uiManager.render(graphics);
    }

    @Override
    public void dispose() {

    }
}
