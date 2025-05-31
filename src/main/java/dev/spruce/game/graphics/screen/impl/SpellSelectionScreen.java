package dev.spruce.game.graphics.screen.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.graphics.screen.Screen;
import dev.spruce.game.input.IMouseInput;
import dev.spruce.game.input.InputManager;
import dev.spruce.game.magic.spell.Spell;
import dev.spruce.game.magic.spell.SpellManager;
import dev.spruce.game.util.GameUtils;

import java.awt.*;

public class SpellSelectionScreen extends Screen implements IMouseInput {

    private static final int BOX_WIDTH = 100;
    private static final int BOX_HEIGHT = 50;
    private static final int PER_ROW = 4; // Number of spells per row

    private SpellManager spellManager;

    @Override
    public void init() {
        InputManager.getInstance().subscribeMouse(this);
        this.spellManager = GameUtils.getPlayer().getSpellManager();
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0x81000001, true));
        graphics.fillRect(0, 0, Window.getInstance().getWidth(), Window.getInstance().getHeight());

        for (Spell spell : spellManager.getSpells()) {
            int index = spellManager.getSpells().indexOf(spell);
            int x = 50 + (index % PER_ROW) * BOX_WIDTH; // 4 spells per row
            int y = 50 + (index / PER_ROW) * BOX_HEIGHT; // new row every 4 spells

            Color textColor = spellManager.getCurrentSpell().equals(spell) ? Color.YELLOW : Color.WHITE;
            FontRenderer.drawString(graphics, spell.getName(), x + 5, y + 5, false, textColor, Fonts.DEFAULT);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
        }
    }

    @Override
    public void dispose() {
        InputManager.getInstance().unsubscribeMouse(this);
    }

    @Override
    public void onMousePress(int button, int x, int y) {

    }

    @Override
    public void onMouseRelease(int button, int mx, int my) {
        for (Spell spell : spellManager.getSpells()) {
            int index = spellManager.getSpells().indexOf(spell);
            int x = 50 + (index % PER_ROW) * BOX_WIDTH;
            int y = 50 + (index / PER_ROW) * BOX_HEIGHT;

            if (mx >= x && mx <= x + BOX_WIDTH && my >= y && my <= y + BOX_HEIGHT) {
                spellManager.swapSpell(spell);
                break; // Exit loop after selecting a spell
            }
        }
    }

    @Override
    public void onMouseClick(int button, int x, int y) {

    }
}
