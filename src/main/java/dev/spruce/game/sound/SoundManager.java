package dev.spruce.game.sound;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.sound.effect.AudioEffect;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SoundManager {

    private static SoundManager instance;

    public static final AudioFormat DEFAULT_FORMAT = new AudioFormat(44100, 16, 2, true, false);

    private CopyOnWriteArrayList<Thread> audioThreads;

    private SoundManager() {
        audioThreads = new CopyOnWriteArrayList<>();
        // Add a shutdown hook to stop all audio threads when the program is closed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Thread thread : audioThreads) {
                thread.interrupt();
            }
        }));
    }

    public void update() {
        audioThreads.removeIf(thread -> !thread.isAlive());
    }

    public void playSound(String soundName, AudioEffect... effects) {
        Thread audioThread = new Thread(new SoundPlayer(soundName, effects));
        audioThreads.add(audioThread);
        audioThread.start();
    }

    public static void init() {
        instance = new SoundManager();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            throw new RuntimeException("Sound manager has not been initialised!");
        }
        return instance;
    }
}
