package dev.spruce.game.item;

import java.io.Serializable;
import java.util.Optional;

public class Inventory implements Serializable {

    private final ItemStack[] items;
    private final int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ItemStack[capacity];
    }

    public boolean addItem(ItemStack stack) {
        // Try to find existing stack.
        Optional<Integer> existingItem = findItem(stack.getItem());
        if (existingItem.isPresent()) {
            // If it exists increase the quantity and return.
            items[existingItem.get()].increaseQuantity(stack.getQuantity());
            return true;
        }

        // Find free slot if existing item is not already in inventory.
        Optional<Integer> freeSlot = getFreeSlot();
        if (freeSlot.isPresent()) {
            // If free slot is found add new stack to that slot.
            items[freeSlot.get()] = stack;
            return true;
        }
        return false;
    }

    public ItemStack getSlot(int slot) {
        return items[slot];
    }

    public boolean isSlotEmpty(int slot) {
        return items[slot] == null;
    }

    public Optional<Integer> findItem(Item item) {
        for (int i = 0; i < capacity; i++) {
            if (isSlotEmpty(i))
                continue;
            if (getSlot(i).getItem().getName().equals(item.getName()))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    public Optional<Integer> getFreeSlot() {
        for (int i = 0; i < capacity; i++) {
            if (items[i] == null) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public int getCapacity() {
        return capacity;
    }

    public ItemStack[] getItems() {
        return items;
    }
}
