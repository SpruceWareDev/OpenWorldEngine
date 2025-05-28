package dev.spruce.game.graphics.ui.hud.effect;

import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.font.FontRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MovingLetterEffect extends UIEffect {

    private final Character character;
    private final Color colour;
    private final float speed;

    private final List<RenderedCharacter> characters;

    public MovingLetterEffect(int x, int y, int width, int height, Character character, Color colour, float speed) {
        super(x, y, width, height);
        this.character = character;
        this.speed = speed;
        this.colour = colour;
        this.characters = new ArrayList<>();
        init();
    }

    private void init() {
        int horizontalNum = getWidth() / 20;
        int verticalNum = getHeight() / 20;
        //Generate characters in a diagonal pattern
        for (int i = 0; i < horizontalNum; i++) {
            //Generate rows of characters
            for (int j = 0; j < verticalNum; j++) {
                float x = getX() + i * 20;
                float y = getY() + j * 20;
                characters.add(new RenderedCharacter(character, x, y));
            }
        }
    }

    @Override
    public void update(double deltaTime) {
        for (RenderedCharacter character1 : characters) {
            // Update the position of each character
            character1.setX((float) (character1.getX() + speed * deltaTime));
            character1.setY((float) (character1.getY() + speed * deltaTime));

            // Reset position if it goes out of bounds
            if (character1.getX() > getX() + getWidth()) {
                character1.setX(getX());
            }
            if (character1.getY() > getY() + getHeight()) {
                character1.setY(getY());
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());

        for (RenderedCharacter character1 : characters) {
            FontRenderer.drawString(
                    graphics, String.valueOf(character1.getCharacter()),
                    (int) character1.getX(), (int) character1.getY(),
                    false, colour, Fonts.DEFAULT
            );
        }
    }
}
