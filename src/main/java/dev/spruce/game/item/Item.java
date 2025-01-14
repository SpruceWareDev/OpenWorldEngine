package dev.spruce.game.item;

import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private final String name;
    private final List<ItemAttribute> attributes;

    public Item(String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
    }

    public void addAttribute(ItemAttribute attribute) {
        attributes.add(attribute);
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

    public List<ItemAttribute> getAttributes() {
        return attributes;
    }
}
