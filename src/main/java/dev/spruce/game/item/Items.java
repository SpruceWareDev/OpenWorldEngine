package dev.spruce.game.item;

import dev.spruce.game.item.attribute.impl.CraftableAttribute;
import dev.spruce.game.item.attribute.impl.FuelAttribute;
import dev.spruce.game.item.attribute.impl.SmeltableAttribute;

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
                    .addAttribute(new CraftableAttribute(4, new RecipeIngredient(LOG, 2)))
                    .build());

    public static final Item WOODEN_STAFF_HEAD =
            registerItem(new ItemBuilder("wooden_staff_head", "Wooden Staff Head")
                    .addAttribute(new FuelAttribute(200))
                    .addAttribute(new CraftableAttribute(1,
                            new RecipeIngredient(LOG, 2),
                            new RecipeIngredient(WOODEN_ROD, 2)
                    ))
                    .build());

    public static final Item WOODEN_STAFF =
            registerItem(new ItemBuilder("wooden_staff", "Wooden Staff")
                    .addAttribute(new CraftableAttribute(1,
                            new RecipeIngredient(WOODEN_ROD, 4),
                            new RecipeIngredient(WOODEN_STAFF_HEAD, 1)
                    ))
                    .addAttribute(new FuelAttribute(80))
                    .build());

    // Station items
    public static final Item CRAFTING_STATION =
            registerItem(new ItemBuilder("crafting_station", "Crafting Station")
                    .build());

    public static Item registerItem(Item item) {
        registeredItems.add(item);
        return item;
    }

    public static List<Item> getRegisteredItems() {
        return registeredItems;
    }
}
