package dev.spruce.game.item;

import dev.spruce.game.item.attribute.ItemAttribute;

public class ItemBuilder {

    private Item item;

    public ItemBuilder(String itemName, String displayName) {
        item = new Item(itemName, displayName);
    }

    public ItemBuilder addAttribute(ItemAttribute attribute) {
        item.addAttribute(attribute);
        return this;
    }

    public Item build() {
        return item;
    }
}
