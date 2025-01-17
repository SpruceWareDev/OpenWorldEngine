package dev.spruce.game.util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class RenderUtils {

    public static void drawRect(Graphics graphics, float x, float y, float width, float height, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        graphics2D.setColor(color);
        graphics2D.fill(rect);
    }

    public static void drawOval(Graphics graphics, float x, float y, float width, float height, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);
        Shape oval = new Ellipse2D.Float(x, y, width, height);
        graphics2D.fill(oval);
    }

    public static void drawTriangle(Graphics graphics, float x, float y, float width, float height, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);

        Path2D.Float triangle = new Path2D.Float();
        triangle.moveTo(x, y + height);
        triangle.lineTo(x + width / 2, y);
        triangle.lineTo(x + width, y + height);
        triangle.closePath();

        graphics2D.fill(triangle);
    }
}
