package dev.spruce.game.item;

import dev.spruce.game.item.attribute.ItemAttribute;
import dev.spruce.game.item.recipe.Recipe;

public class ItemBuilder {

    private Item item;

    public ItemBuilder(String itemName, String displayName) {
        item = new Item(itemName, displayName);
    }

    public ItemBuilder addAttribute(ItemAttribute attribute) {
        item.addAttribute(attribute);
        return this;
    }

    public ItemBuilder addRecipe(Recipe recipe) {
        item.addRecipe(recipe);
        return this;
    }

    public Item build() {
        return item;
    }
}
