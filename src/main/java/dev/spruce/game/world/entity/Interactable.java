package dev.spruce.game.world.entity;

public interface Interactable {
    void interact();
    float radius(); // The radius within which the entity can be interacted with
}
