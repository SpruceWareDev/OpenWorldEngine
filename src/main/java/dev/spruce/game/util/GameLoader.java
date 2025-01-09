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
        if (newGame) {
            this.loadedGame = new GameState(name);
            this.loadedGame.init();
        } else {
            try {
                this.loadedGame = FileManager.loadGame(this.name);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Failed to load existing world:");
                throw new RuntimeException(e);
            }
        }
    }

    public GameState getLoadedGame() {
        return loadedGame;
    }
}
