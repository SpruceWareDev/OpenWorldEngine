package dev.spruce.game.input;

import com.raylib.Raylib;
import dev.spruce.game.BuildVersion;
import dev.spruce.game.Game;

import java.util.concurrent.CopyOnWriteArrayList;

public class InputManager {

    private static InputManager instance;

    private boolean[] keys;
    private CopyOnWriteArrayList<IKeyInput> keySubscribers;
    private CopyOnWriteArrayList<IMouseInput> mouseSubscribers;

    private int mouseX, mouseY;

    public void init() {
        keys = new boolean[512];
        keySubscribers = new CopyOnWriteArrayList<>();
        mouseSubscribers = new CopyOnWriteArrayList<>();
    }

    public void subscribeKey(IKeyInput input) {
        keySubscribers.add(input);
    }

    public void unsubscribeKey(IKeyInput input) {
        keySubscribers.remove(input);
    }

    public void subscribeMouse(IMouseInput input) {
        mouseSubscribers.add(input);
    }

    public void unsubscribeMouse(IMouseInput input) {
        mouseSubscribers.remove(input);
    }

    public void unsubscribeAll() {
        keySubscribers.clear();
        mouseSubscribers.clear();
    }

    public boolean isSubscribedKey(IKeyInput input) {
        return keySubscribers.contains(input);
    }

    public boolean isSubscribedMouse(IMouseInput input) {
        return mouseSubscribers.contains(input);
    }

    public void pollInputs() {
        for (int i = 0; i < keys.length; i++) {
            boolean previousState = keys[i];
            boolean nextState = Raylib.IsKeyDown(i);
            if (previousState && !nextState) {
                keyReleased(i);
            } else if (!previousState && nextState) {
                keyPressed(i);
            }
            keys[i] = Raylib.IsKeyDown(i);

        }
    }

    public void keyPressed(int keyCode) {
        for (IKeyInput input : keySubscribers) {
            input.onKeyPress(keyCode);
        }

        if (Game.BUILD_VERSION.equals(BuildVersion.DEVELOPMENT)) {
            switch (keyCode) {
                case Raylib.KEY_F3 -> Game.debug = !Game.debug;
                case Raylib.KEY_F5 -> Game.devInvincibility = !Game.devInvincibility;
            }
        }
    }

    public void keyReleased(int keyCode) {
        for (IKeyInput input : keySubscribers) {
            input.onKeyRelease(keyCode);
        }
    }

    /*
    public void mouseClicked(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMouseClick(e.getButton(), e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMousePress(e.getButton(), e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMouseRelease(e.getButton(), e.getX(), e.getY());
        }
    }

     */

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isMouseOver(int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }
}
