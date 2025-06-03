package dev.spruce.game.assets.managers;

import dev.spruce.game.assets.AssetManager;

import java.util.HashMap;

public class SoundAssetManager extends AssetManager<String, String> {

    private HashMap<String, String> sounds;

    public SoundAssetManager() {
        sounds = new HashMap<>();
        sounds.put("test", "assets/sounds/test.wav");
        sounds.put("fireball", "assets/sounds/fireball.wav");
    }

    @Override
    public String getAsset(String assetId) {
        return sounds.get(assetId);
    }

    @Override
    public void dispose() {

    }
}
