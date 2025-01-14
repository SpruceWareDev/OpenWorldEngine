package dev.spruce.game.item;

import dev.spruce.game.item.attribute.impl.FuelAttribute;
import dev.spruce.game.item.attribute.impl.SmeltableAttribute;

public class Items {
    public static final Item LOG =
            new ItemBuilder("log", "Log")
                    .addAttribute(new SmeltableAttribute(60, Items.CHARCOAL, 280))
                    .addAttribute(new FuelAttribute(240))
                    .build();

    public static final Item CHARCOAL =
            new ItemBuilder("charcoal", "Charcoal")
                    .addAttribute(new FuelAttribute(600))
                    .build();
}
