package dev.spruce.game.graphics.font;

import java.awt.*;

public class FontRenderer {

	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(c);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int x = xPos;
		int y = yPos + fm.getHeight();
		if(center) {
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		g2d.drawString(text, x, y);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	public static void drawStringCentred(Graphics g, String text, int xPos, int yPos, Color c, Font font) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(c);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int x = xPos - fm.stringWidth(text) / 2;
		int y = yPos + fm.getHeight();
		g2d.drawString(text, x, y);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
}