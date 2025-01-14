package dev.spruce.game.graphics.screen;

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

    public void setScreen(Screen screen) {
        if (currentScreen != null)
            currentScreen.dispose();
        currentScreen = screen;
        currentScreen.init();
    }

    public void closeScreen() {
        if (currentScreen != null)
            currentScreen.dispose();
        currentScreen = null;
    }
}
