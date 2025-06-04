package dev.spruce.game.state.impl;

import com.raylib.Colors;
import com.raylib.Raylib;
import dev.spruce.game.Game;
import dev.spruce.game.state.State;
import dev.spruce.game.util.GameLoader;

public class GameLoadingState extends State {

    private Thread loadingThread;
    private GameLoader gameLoader;

    private final String name;
    private final boolean newGame;
    private int seed = 0;

    private StringBuilder loadingDots;
    private int loadingDotTimer = 0;

    public GameLoadingState(String name, boolean newGame) {
        this.name = name;
        this.newGame = newGame;
    }

    public GameLoadingState(String name, int seed) {
        this.name = name;
        this.newGame = true;
        this.seed = seed;
    }

    @Override
    public void init() {
        this.loadingDots = new StringBuilder();

        this.gameLoader = newGame ? new GameLoader(name, seed) : new GameLoader(name, false);
        this.loadingThread = new Thread(gameLoader);
        this.loadingThread.start();
    }

    @Override
    public void update(double delta) {
        loadingDotTimer++;
        if (loadingDotTimer >= 30) {
            if (loadingDots.toString().equals("...")) {
                loadingDots = new StringBuilder();
            } else {
                loadingDots.append(".");
            }
            loadingDotTimer = 0;
        }

        if (!loadingThread.isAlive()) {
            Game.getStateManager().setState(this.gameLoader.getLoadedGame(), false);
        }
    }

    @Override
    public void render() {
        Raylib.DrawText("Loading" + loadingDots.toString(), 10, 10, 22, Colors.WHITE);
    }

    @Override
    public void dispose() {

    }
}
