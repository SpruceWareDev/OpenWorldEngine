package dev.spruce.game.util;

import dev.spruce.game.file.FileManager;
import dev.spruce.game.state.impl.GameState;

import java.io.IOException;

public class GameLoader implements Runnable {

    private volatile GameState loadedGame;

    private final String name;
    private final boolean newGame;
    private int seed = 0;

    public GameLoader(String name, boolean newGame) {
        this.name = name;
        this.newGame = newGame;
    }

    public GameLoader(String name, int seed) {
        this.name = name;
        this.newGame = true;
        this.seed = seed;
    }

    @Override
    public void run() {
        this.loadedGame = new GameState(name, newGame, seed);
        this.loadedGame.init();
    }

    public GameState getLoadedGame() {
        return loadedGame;
    }
}
