package dev.spruce.game.item.attribute.impl;

import dev.spruce.game.item.Item;
import dev.spruce.game.item.RecipeIngredient;
import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CraftableAttribute extends ItemAttribute {

    private final List<RecipeIngredient> ingredients;
    private final int resultQuantity;

    public CraftableAttribute(int resultQuantity, RecipeIngredient... ingredients) {
        super(AttributeType.CRAFTABLE);
        this.resultQuantity = resultQuantity;
        this.ingredients = Arrays.stream(ingredients).toList();
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public int getResultQuantity() {
        return resultQuantity;
    }
}
