package dev.spruce.game.state.impl;

import dev.spruce.game.Game;
import dev.spruce.game.assets.Fonts;
import dev.spruce.game.graphics.Window;
import dev.spruce.game.graphics.font.FontRenderer;
import dev.spruce.game.state.State;
import dev.spruce.game.util.GameLoader;

import java.awt.*;

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
    public void render(Graphics graphics) {
        FontRenderer.drawStringCentred(graphics, "Loading" + loadingDots.toString(), Window.getInstance().getWidth() / 2, Window.getInstance().getHeight() / 3, Color.WHITE, Fonts.TITLE);
    }

    @Override
    public void dispose() {

    }
}
