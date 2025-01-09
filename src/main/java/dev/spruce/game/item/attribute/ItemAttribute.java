package dev.spruce.game.item.attribute;

import java.io.Serializable;

public abstract class ItemAttribute implements Serializable {

    private final AttributeType type;

    public ItemAttribute(AttributeType type) {
        this.type = type;
    }

    public AttributeType getType() {
        return type;
    }
}
