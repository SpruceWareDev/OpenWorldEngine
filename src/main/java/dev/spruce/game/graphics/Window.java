package dev.spruce.game.graphics;

import javax.swing.*;

public class Window extends JFrame {

    private static Window instance;

    public Window(int width, int height, String title) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void init(int width, int height, String title) {
        if (instance != null) {
            throw new IllegalStateException("Window has already been initialized!");
        }
        instance = new Window(width, height, title);
    }

    public static Window getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Window has not been initialized yet!");
        }
        return instance;
    }
}
