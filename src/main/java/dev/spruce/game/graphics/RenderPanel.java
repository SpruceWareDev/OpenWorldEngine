package dev.spruce.game.graphics;

import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.input.InputManager;

import java.awt.*;

public class RenderPanel {

    public static final int FPS_TARGET = 240;

    private final Game game;

    public RenderPanel(Game game, String windowTitle, int width, int height) {
        this.game = game;
        initRaylib(windowTitle, width, height);
    }

    private void initRaylib(String windowTitle, int width, int height) {
        Raylib.InitWindow(width, height, windowTitle);
        Raylib.SetTargetFPS(FPS_TARGET);
    }

    public void run() {
        while (!Raylib.WindowShouldClose()) {
            game.update(Raylib.GetFrameTime());
            Raylib.BeginDrawing();
            game.render();
            Raylib.EndDrawing();
        }
        game.dispose();
        Raylib.CloseWindow();
    }
}
