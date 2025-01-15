package dev.spruce.game.item;

import dev.spruce.game.entity.impl.station.StationType;
import dev.spruce.game.item.attribute.impl.FuelAttribute;
import dev.spruce.game.item.attribute.impl.SmeltableAttribute;
import dev.spruce.game.item.recipe.Recipe;
import dev.spruce.game.item.recipe.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class Items {

    private static final List<Item> registeredItems = new ArrayList<>();

    public static final Item LOG =
            registerItem(new ItemBuilder("log", "Log")
                    .addAttribute(new SmeltableAttribute(60, Items.CHARCOAL, 280))
                    .addAttribute(new FuelAttribute(240))
                    .build());

    public static final Item CHARCOAL =
            registerItem(new ItemBuilder("charcoal", "Charcoal")
                    .addAttribute(new FuelAttribute(600))
                    .build());

    public static final Item WOODEN_ROD =
            registerItem(new ItemBuilder("wooden_rod", "Wooden Rod")
                    .addAttribute(new FuelAttribute(60))
                    .addRecipe(new Recipe(Items.WOODEN_ROD, 8, StationType.CRAFTING, new RecipeIngredient(Items.LOG, 1)))
                    .build());

    // Station items
    public static final Item CRAFTING_STATION =
            registerItem(new ItemBuilder("crafting_station", "Crafting Station")
                    .addRecipe(new Recipe(Items.CRAFTING_STATION, 1, StationType.NONE, new RecipeIngredient(Items.LOG, 5)))
                    .build());

    public static Item registerItem(Item item) {
        registeredItems.add(item);
        return item;
    }

    public static List<Item> getRegisteredItems() {
        return registeredItems;
    }
}
