package dev.spruce.game.world.item;

public class Item {

    private final String name;
    private final String displayName;
    private final String description;
    private final int maxStackSize;

    public Item(String name, String displayName, String description, int maxStackSize) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.maxStackSize = maxStackSize;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
}
