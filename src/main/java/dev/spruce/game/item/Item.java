package dev.spruce.game.item;

import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;
import dev.spruce.game.item.recipe.Recipe;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private final String name;
    private final String displayName;
    private final List<ItemAttribute> attributes;
    private final List<Recipe> recipes;

    public Item(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.attributes = new ArrayList<>();
        this.recipes = new ArrayList<>();
    }

    public void addAttribute(ItemAttribute attribute) {
        attributes.add(attribute);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public boolean hasAttribute(AttributeType type) {
        return attributes.stream().anyMatch(attribute -> attribute.getType().equals(type));
    }

    public ItemAttribute getAttribute(AttributeType type) {
        return attributes.stream().filter(attribute -> attribute.getType().equals(type)).toList().get(0);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<ItemAttribute> getAttributes() {
        return attributes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
