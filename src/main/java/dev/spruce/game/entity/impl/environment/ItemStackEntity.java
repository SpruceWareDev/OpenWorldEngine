package dev.spruce.game.entity.impl.environment;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class ItemStackEntity extends Entity {

    private final ItemStack itemStack;

    public ItemStackEntity(GameState gameState, ItemStack itemStack, float x, float y, float width, float height) {
        super(gameState, x, y, width, height, 1);
        this.itemStack = itemStack;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics, Camera camera) {

    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}