package dev.spruce.game.graphics.screen;

import java.awt.*;
import java.util.Optional;

public class ScreenManager {

    private Optional<Screen> currentScreen = Optional.empty();

    public void update(double delta) {
        currentScreen.ifPresent(screen -> screen.update(delta));
    }

    public void render(Graphics graphics) {
        currentScreen.ifPresent((screen -> screen.render(graphics)));
    }

    public void setScreen(Screen screen) {
        currentScreen.ifPresent(Screen::dispose);
        currentScreen = Optional.of(screen);
        currentScreen.ifPresent(Screen::init);
    }

    public void closeScreen() {
        currentScreen.ifPresent(Screen::dispose);
        currentScreen = Optional.empty();
    }
}
