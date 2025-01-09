package dev.spruce.game.item.attribute.impl;

import dev.spruce.game.item.Item;
import dev.spruce.game.item.attribute.AttributeType;
import dev.spruce.game.item.attribute.ItemAttribute;

public class SmeltableAttribute extends ItemAttribute {

    private final int ticksToSmelt;
    private final Item smeltResult;
    private final int requiredTemperature;

    public SmeltableAttribute(int ticksToSmelt, Item smeltResult, int requiredTemperature) {
        super(AttributeType.SMELTABLE);
        this.ticksToSmelt = ticksToSmelt;
        this.smeltResult = smeltResult;
        this.requiredTemperature = requiredTemperature;
    }

    public int getTicksToSmelt() {
        return ticksToSmelt;
    }

    public Item getSmeltResult() {
        return smeltResult;
    }

    public int getRequiredTemperature() {
        return requiredTemperature;
    }
}
