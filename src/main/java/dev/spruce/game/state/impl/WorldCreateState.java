package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIManager;
import dev.spruce.game.graphics.ui.component.impl.UIButton;
import dev.spruce.game.graphics.ui.component.impl.UITextBox;
import dev.spruce.game.state.State;

import java.awt.*;

public class WorldCreateState extends State {

    private UIManager uiManager;
    private UITextBox worldName;
    private UITextBox worldSeed;

    @Override
    public void init() {
        uiManager = new UIManager();

        worldName = new UITextBox(
                0, 0, 200, 40,
                ScreenSnapPoint.CENTER,
                UITextBox.AllowedChars.ALPHA_NUMERIC
        );
        uiManager.addElement(worldName);

        worldSeed = new UITextBox(
                0, 0, 200, 40,
                ScreenSnapPoint.CENTER,
                UITextBox.AllowedChars.NUMERIC
        );
        uiManager.addElement(worldSeed);

        uiManager.addElement(new UIButton(
                "Create",
                Color.BLUE,
                0, 0, 200, 80,
                ScreenSnapPoint.CENTER,
                () -> Game.getStateManager()
                        .setState(
                                new GameLoadingState(worldName.getText(), Integer.parseInt(worldSeed.getText())), true
                        )
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
