package dev.spruce.game.input;

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

    public void subscribe(IKeyInput input) {
        keySubscribers.add(input);
    }

    public void unsubscribe(IKeyInput input) {
        keySubscribers.remove(input);
    }

    public void subscribe(IMouseInput input) {
        mouseSubscribers.add(input);
    }

    public void unsubscribe(IMouseInput input) {
        mouseSubscribers.remove(input);
    }

    public void unsubscribeAll() {
        keySubscribers.clear();
        mouseSubscribers.clear();
    }

    public boolean isKeyDown(int keyCode) {
        if (keyCode > 0 && keyCode < keys.length - 1)
            return keys[keyCode];
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (IKeyInput input : keySubscribers) {
            input.onKeyTyped(e.getKeyCode());
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
        System.out.println("Mouse click: " + e.getButton() + " at " + e.getX() + ", " + e.getY());
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
