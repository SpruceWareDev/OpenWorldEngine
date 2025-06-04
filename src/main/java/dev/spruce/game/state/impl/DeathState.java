package dev.spruce.game.state.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.state.State;

public class DeathState extends State {

    private final GameState gameState;

    public DeathState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {
        //uiManager.update();
    }

    @Override
    public void render() {
        Raylib.DrawText(
                "YOU DIED",
                Raylib.GetRenderWidth() / 2 - Raylib.MeasureText("YOU DIED", 22) / 2,
                Raylib.GetRenderHeight() / 3, 22, Colors.WHITE
        );

        Raylib.Rectangle respawnButton = new Raylib.Rectangle()
                .x((float) Raylib.GetRenderWidth() / 2 - 200)
                .y((float) Raylib.GetRenderHeight() / 2)
                .width(400).height(32);
        if (Raylib.GuiButton(respawnButton, "Respawn") == 1) {
            gameState.getPlayer().setHealth(gameState.getPlayer().getMaxHealth());
            Game.getStateManager().setState(gameState, false);
        }
    }

    @Override
    public void dispose() {

    }
}
