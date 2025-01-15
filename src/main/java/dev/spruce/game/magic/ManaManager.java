package dev.spruce.game.magic;

import java.io.Serializable;

public class ManaManager implements Serializable {

    private int mana;
    private int maxMana;

    private int manaRegenTicks = 0;

    public ManaManager(int maxMana) {
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    public void update() {
        manaRegenTicks++;
        if (mana < maxMana && manaRegenTicks > 100) {
            mana++;
            manaRegenTicks = 0;
        }
    }

    public void addMana(int amount) {
        mana += amount;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }

    public boolean removeMana(int amount) {
        if (mana - amount < 0) {
            return false;
        }
        mana -= amount;
        return true;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
