package dev.spruce.game.util;

import dev.spruce.game.file.FileManager;
import dev.spruce.game.state.impl.GameState;

import java.io.IOException;

public class GameLoader implements Runnable {

    private volatile GameState loadedGame;

    private final String name;
    private final boolean newGame;

    public GameLoader(String name, boolean newGame) {
        this.name = name;
        this.newGame = newGame;
    }

    @Override
    public void run() {
        this.loadedGame = new GameState(name, newGame);
        this.loadedGame.init();
    }

    public GameState getLoadedGame() {
        return loadedGame;
    }
}
