package dev.spruce.game.graphics.ui.hud.effect;

public class RenderedCharacter {

    private Character character;
    private float x, y;

    public RenderedCharacter(Character character, float x, float y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
