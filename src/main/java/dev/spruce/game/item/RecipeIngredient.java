package dev.spruce.game.item;

public class RecipeIngredient {

    private final Item item;
    private final int requiredAmount;

    public RecipeIngredient(Item item, int requiredAmount) {
        this.item = item;
        this.requiredAmount = requiredAmount;
    }

    public Item getItem() {
        return item;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }
}
