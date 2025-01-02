package dev.spruce.game.graphics;

import dev.spruce.game.Game;
import dev.spruce.game.input.InputManager;

import java.awt.*;

public class RenderPanel {

    private Game game;
    private Canvas canvas;
    private Graphics graphics;
    private boolean running;

    public RenderPanel(Game game) {
        this.game = game;
        initCanvas();
        running = true;
    }

    private void initCanvas() {
        canvas = new Canvas();
        canvas.setSize(Window.getInstance().getSize());
        canvas.addKeyListener(InputManager.getInstance());
        canvas.addMouseListener(InputManager.getInstance());
        canvas.addMouseMotionListener(InputManager.getInstance());
        Window.getInstance().add(canvas);
        canvas.createBufferStrategy(3);
        canvas.setFocusable(true);
        canvas.requestFocus();
        canvas.setVisible(true);
    }

    public void run() {
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update(delta);
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Window.getInstance().setTitle(String.format("%s (%s ups, %s fps)", Game.FORMATTED_NAME, updates, frames));
                updates = 0;
                frames = 0;
            }
        }
    }

    private void update(double delta) {
        game.update(delta);
    }

    private void render() {
        graphics = canvas.getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Window.getInstance().getSize().width, Window.getInstance().getSize().height);
        game.render(graphics);
        canvas.getBufferStrategy().show();
    }
}
