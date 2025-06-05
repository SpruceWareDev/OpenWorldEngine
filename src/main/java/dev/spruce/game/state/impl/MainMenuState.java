package dev.spruce.game.state.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.state.State;

public class MainMenuState extends State {

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render() {
        Raylib.DrawText(Game.FORMATTED_NAME,
                Raylib.GetRenderWidth() / 2 - Raylib.MeasureText(Game.FORMATTED_NAME, 32) / 2,
                Raylib.GetRenderHeight() / 4, 32, Colors.WHITE
        );

        Raylib.Rectangle startButton = new Raylib.Rectangle()
                .x(((float) Raylib.GetRenderWidth() / 2) - 200)
                .y((float) Raylib.GetRenderHeight() / 2)
                .width(400).height(32);
        if(Raylib.GuiButton(startButton, "Start Run") == 1) {
            Game.getStateManager().setState(new GameState("AHHHH", 1234));
        }

        Raylib.Rectangle journalButton = new Raylib.Rectangle()
                .x(((float) Raylib.GetRenderWidth() / 2) - 200)
                .y((float) Raylib.GetRenderHeight() / 2 + 34)
                .width(400).height(32);
        if (Raylib.GuiButton(journalButton, "Journal") == 1) {
            System.out.println("Journal button pressed :3");
        }

        Raylib.Rectangle quitButton = new Raylib.Rectangle()
                .x(((float) Raylib.GetRenderWidth() / 2) - 200)
                .y((float) Raylib.GetRenderHeight() / 2 + 68)
                .width(400).height(32);
        if (Raylib.GuiButton(quitButton, "Quit") == 1) {
            System.exit(0);
        }
    }

    @Override
    public void dispose() {

    }
}
