package dev.spruce.game.util;

import java.util.HashMap;

public class Profiler {

    private HashMap<String, Integer> profiles;
    private HashMap<String, Long> profileStart;

    public void init() {
        profiles = new HashMap<>();
        profileStart = new HashMap<>();
    }

    public void startProfile(String tag) {
        if (!profiles.containsKey(tag)) {
            profiles.put(tag, 0);
            profileStart.put(tag, System.nanoTime());
        } else {
            profileStart.replace(tag, System.nanoTime());
        }
    }

    public void endProfile(String tag) {
        if (profiles.containsKey(tag)) {
            profiles.replace(tag, (int) (System.nanoTime() - profileStart.get(tag)));
        }
    }

    public HashMap<String, Integer> getProfiles() {
        return profiles;
    }
}
