package dev.spruce.game.util;

import dev.spruce.game.graphics.RenderPanel;

public class TimerUtils {

    public static int ticksFromSeconds(float seconds) {
        return (int) (seconds * RenderPanel.TICK_RATE);
    }

    public static String formatTime(long ticks) {
        int seconds = (int) (ticks / RenderPanel.TICK_RATE);
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
