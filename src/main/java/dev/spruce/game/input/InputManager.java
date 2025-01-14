package dev.spruce.game.input;

import dev.spruce.game.Game;

import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

    private static InputManager instance;

    private boolean[] keys;
    private CopyOnWriteArrayList<IKeyInput> keySubscribers;
    private CopyOnWriteArrayList<IMouseInput> mouseSubscribers;

    private int mouseX, mouseY;

    public void init() {
        keys = new boolean[256];
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

    public boolean isKeyDown(int keyCode) {
        if (keyCode > 0 && keyCode < keys.length - 1)
            return keys[keyCode];
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (IKeyInput input : keySubscribers) {
            input.onKeyTyped(e.getKeyCode(), e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() > 0 && e.getKeyCode() < keys.length - 1) {
            keys[e.getKeyCode()] = true;
            for (IKeyInput input : keySubscribers) {
                input.onKeyPress(e.getKeyCode());
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_F3) {
            Game.debug = !Game.debug;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() > 0 && e.getKeyCode() < keys.length - 1) {
            keys[e.getKeyCode()] = false;
            for (IKeyInput input : keySubscribers) {
                input.onKeyRelease(e.getKeyCode());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMouseClick(e.getButton(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMousePress(e.getButton(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (IMouseInput input : mouseSubscribers) {
            input.onMouseRelease(e.getButton(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

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
