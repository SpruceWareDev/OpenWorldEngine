package dev.spruce.game.item;

import dev.spruce.game.item.attribute.ItemAttribute;

public class ItemBuilder {

    private Item item;

    public ItemBuilder(String itemName) {
        item = new Item(itemName);
    }

    public ItemBuilder addAttribute(ItemAttribute attribute) {
        item.addAttribute(attribute);
        return this;
    }

    public Item build() {
        return item;
    }
}
