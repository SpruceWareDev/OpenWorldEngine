package dev.spruce.game.graphics.screen.impl;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.screen.Screen;
import dev.spruce.game.item.Item;
import dev.spruce.game.item.Items;
import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;
import dev.spruce.game.item.attribute.impl.CraftableAttribute;
import dev.spruce.game.util.RenderUtils;

import java.awt.*;
import java.util.List;

public class CraftingStationScreen extends Screen {

    private static final int WIDTH = 450;

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        int windowWidth = Window.getInstance().getWidth();
        int windowHeight = Window.getInstance().getHeight();

        int topBarX = windowWidth / 2 - WIDTH / 2;
        int topBarY = windowHeight / 4;
        graphics.setColor(new Color(0,0,0,180));
        graphics.fillRoundRect(topBarX, topBarY, WIDTH, 45, 12, 12);
        FontRenderer.drawString(graphics, "Crafting", topBarX + 12, topBarY - 3, false, Color.white, Fonts.LARGE);

        int recipeBaseY = topBarY + 50;
        int i = 0;
        for (Item item : Items.getRegisteredItems()) {
            if (!item.hasAttribute(AttributeType.CRAFTABLE))
                continue;

            List<ItemAttribute> craftableAttributes = item.getAttributesOfType(AttributeType.CRAFTABLE);

            for (ItemAttribute attribute : craftableAttributes) {
                int recipeY = recipeBaseY + (i * 50);
                graphics.setColor(new Color(0,0,0,180));
                graphics.fillRoundRect(topBarX, recipeY, WIDTH, 45, 12, 12);
                FontRenderer.drawString(graphics, item.getDisplayName(), topBarX + 12, recipeY, false, Color.white, Fonts.SMALL);
                i++;
            }
        }
    }

    @Override
    public void dispose() {

    }
}
