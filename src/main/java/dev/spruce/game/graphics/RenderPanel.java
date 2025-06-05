package dev.spruce.game.graphics;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;

public class RenderPanel {

    public static final int FPS_TARGET = 240;
    public static final int TICK_RATE = 60;

    private final Game game;
    //private Thread tickThread;

    public RenderPanel(Game game, String windowTitle, int width, int height) {
        this.game = game;
        initRaylib(windowTitle, width, height);
    }

    private void initRaylib(String windowTitle, int width, int height) {
        Raylib.InitWindow(width, height, windowTitle);
        Raylib.SetTargetFPS(FPS_TARGET);
        Raylib.SetExitKey(Raylib.KEY_NULL);
    }

    public void run() {
        while (!Raylib.WindowShouldClose()) {
            game.update(Raylib.GetFrameTime());

            Raylib.BeginDrawing();
            Raylib.ClearBackground(Colors.BLACK);
            game.render();
            Raylib.DrawFPS(10, 10);
            Raylib.EndDrawing();
        }
        game.dispose();
        Raylib.CloseWindow();
    }

    /*
    private class TickHandler implements Runnable {

        @Override
        public void run() {
            double ns = 1000000000.0 / TICK_RATE;
            double delta = 0;
            double lastTime = System.nanoTime();

            while (!Raylib.WindowShouldClose()) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    game.update(delta);
                    delta--;
                }
                now = System.nanoTime();
            }
        }
    }

     */
}
