package dev.spruce.game.graphics.screen;

import dev.spruce.game.Game;

import java.awt.*;
import java.util.Optional;

public class ScreenManager {

    private Screen currentScreen;

    public void update(double delta) {
        if (currentScreen == null) return;
        currentScreen.update(delta);
    }

    public void render(Graphics graphics) {
        if (currentScreen == null) return;
        currentScreen.render(graphics);
    }

    public void setScreen(Screen screen, boolean pauseCurrentState) {
        if (currentScreen != null) currentScreen.dispose();
        if (pauseCurrentState) Game.getStateManager().togglePause();
        currentScreen = screen;
        currentScreen.init();
    }

    public void closeScreen() {
        if (currentScreen != null)
            currentScreen.dispose();
        if (Game.getStateManager().isPaused()) Game.getStateManager().togglePause();
        currentScreen = null;
    }
}
