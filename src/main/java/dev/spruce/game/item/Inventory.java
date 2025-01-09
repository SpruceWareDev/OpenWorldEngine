package dev.spruce.game.item;

import java.io.Serializable;

public class Inventory implements Serializable {

    private final ItemStack[] items;
    private final int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ItemStack[capacity];
    }

    public boolean addItem(ItemStack stack) {
        int freeSlot = getFreeSlot();
        if (freeSlot == -1) return false;
        items[freeSlot] = stack;
        return true;
    }

    public ItemStack getSlot(int slot) {
        return items[slot];
    }

    public int getFreeSlot() {
        for (int i = 0; i < capacity; i++) {
            if (items[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int getCapacity() {
        return capacity;
    }

    public ItemStack[] getItems() {
        return items;
    }
}
