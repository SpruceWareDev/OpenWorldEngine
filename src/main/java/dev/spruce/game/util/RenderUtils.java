package dev.spruce.game.util;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RenderUtils {

    public static void drawRect(Graphics graphics, float x, float y, float width, float height, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        graphics2D.setColor(color);
        graphics2D.fill(rect);
    }
}
