package dev.spruce.game.item.recipe;

import dev.spruce.game.item.Item;

import java.io.Serializable;

public class RecipeIngredient implements Serializable {

    private final Item item;
    private final int amount;

    public RecipeIngredient(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
