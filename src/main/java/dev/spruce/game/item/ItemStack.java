package dev.spruce.game.item;

import java.io.Serializable;

public class ItemStack implements Serializable {

    private final Item item;
    private int quantity;

    public ItemStack(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemStack(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
