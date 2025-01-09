package dev.spruce.game.item.attribute.impl;

import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;

public class FuelAttribute extends ItemAttribute {

    private final int burnTicks;

    public FuelAttribute(int burnTicks) {
        super(AttributeType.FUEL);
        this.burnTicks = burnTicks;
    }

    public int getBurnTicks() {
        return burnTicks;
    }
}
