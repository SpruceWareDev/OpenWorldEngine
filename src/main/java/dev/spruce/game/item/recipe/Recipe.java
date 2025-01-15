package dev.spruce.game.item.recipe;

import dev.spruce.game.entity.impl.station.StationType;
import dev.spruce.game.item.Item;

import java.io.Serializable;

public class Recipe implements Serializable {

    private final RecipeIngredient[] ingredients;
    private final Item result;
    private final int resultAmount;
    private final StationType stationType;

    public Recipe(Item result, int resultAmount, StationType stationType, RecipeIngredient... ingredients) {
        this.ingredients = ingredients;
        this.result = result;
        this.stationType = stationType;
        this.resultAmount = resultAmount;
    }

    public RecipeIngredient[] getIngredients() {
        return ingredients;
    }

    public Item getResult() {
        return result;
    }

    public int getResultAmount() {
        return resultAmount;
    }

    public StationType getStationType() {
        return stationType;
    }
}
