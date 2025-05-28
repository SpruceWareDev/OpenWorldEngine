package dev.spruce.game;

public enum BuildVersion {
    ALPHA("Alpha"),
    BETA("Beta"),
    RELEASE("Release"),
    DEVELOPMENT("Development");

    String name;

    BuildVersion(String name) {
        this.name = name;
    }
}
