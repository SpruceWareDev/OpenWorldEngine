package dev.spruce.game.graphics.ui.component;

import com.raylib.Raylib;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private List<UIElement> elements;

    public UIManager() {
        this.elements = new ArrayList<>();
    }

    public void addElement(UIElement element) {
        elements.add(element);
    }

    public void update() {
        for (UIElement element : elements) {
            element.update();
        }
    }

    public void render() {
        for (ScreenSnapPoint snapPoint : ScreenSnapPoint.values()) {
            switch (snapPoint) {
                case CENTER -> {
                    int i = 0;
                    for (UIElement element : getBySnapPoint(snapPoint)) {
                        int x = (Raylib.GetRenderWidth() / 2) - (element.getWidth() / 2);
                        int y = (Raylib.GetRenderHeight() / 2) + ((element.getHeight() + 2) * i);
                        element.setX(x);
                        element.setY(y);
                        element.render();
                        i++;
                    }
                }
                case NONE -> getBySnapPoint(snapPoint).forEach(UIElement::render);
            }
        }
    }

    public void dispose() {
        elements.forEach(UIElement::dispose);
    }

    public List<UIElement> getBySnapPoint(ScreenSnapPoint snapPoint) {
        return elements.stream().filter(element -> element.getSnapPoint() == snapPoint).toList();
    }
}
