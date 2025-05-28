package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.entity.impl.Player;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIManager;
import dev.spruce.game.graphics.ui.component.impl.UIButton;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.State;

import java.awt.*;

public class DeathState extends State {

    private final GameState gameState;
    private UIManager uiManager;

    public DeathState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void init() {
        this.uiManager = new UIManager();
        this.uiManager.addElement(
                new UIButton(
                        "Respawn",
                        Color.BLUE,
                        Window.getInstance().getWidth() / 2 - 50,
                        Window.getInstance().getHeight() / 2 + 50,
                        100, 40,
                        ScreenSnapPoint.CENTER,
                        () -> {
                            gameState.getPlayer().setHealth(gameState.getPlayer().getMaxHealth());
                            InputManager.getInstance().subscribeKey(gameState);
                            InputManager.getInstance().subscribeMouse(gameState);
                            Game.getStateManager().setState(gameState, false);
                        }
                )
        );
    }

    @Override
    public void update(double delta) {
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(
                graphics, "YOU DIED",
                Window.getInstance().getWidth() / 2, Window.getInstance().getHeight() / 4,
                Color.WHITE, Fonts.TITLE
        );
        uiManager.render(graphics);
    }

    @Override
    public void dispose() {

    }
}
