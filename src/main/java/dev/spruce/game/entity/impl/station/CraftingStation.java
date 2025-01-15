package dev.spruce.game.entity.impl.station;

import dev.spruce.game.Game;
import dev.spruce.game.entity.impl.environment.ItemStackEntity;
import dev.spruce.game.graphics.Camera;
import dev.spruce.game.item.ItemStack;
import dev.spruce.game.item.Items;
import dev.spruce.game.state.impl.GameState;

import java.awt.*;

public class CraftingStation extends StationEntity {

    public CraftingStation(float x, float y) {
        super(StationType.CRAFTING, x, y);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics, Camera camera) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect((int) (getX() - camera.getX()), (int) (getY() - camera.getY()), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void onDeath() {
        GameState gs = Game.getStateManager().getGameState();
        gs.getEntityManager().spawn(new ItemStackEntity(new ItemStack(Items.CRAFTING_STATION), getX(), getY()));
        gs.getEntityManager().despawn(this);
    }

    @Override
    public void interact() {

    }
}
