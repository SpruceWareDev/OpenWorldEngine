package dev.spruce.game.input;

public interface IMouseInput {

        void onMousePress(int button, int x, int y);

        void onMouseRelease(int button, int x, int y);

        void onMouseClick(int button, int x, int y);
}
