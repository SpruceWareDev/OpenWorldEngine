package dev.spruce.game.state.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.ui.component.ScreenSnapPoint;
import dev.spruce.game.graphics.ui.component.UIManager;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.state.State;

import java.awt.*;

public class DeathState extends State {

    private final GameState gameState;
    //private UIManager uiManager;

    public DeathState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void init() {
        /*
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

         */
    }

    @Override
    public void update(double delta) {
        //uiManager.update();
    }

    @Override
    public void render() {
        Raylib.DrawText("YOU DIED", 10, 10, 22, Colors.WHITE);
        //uiManager.render(graphics);
    }

    @Override
    public void dispose() {

    }
}
