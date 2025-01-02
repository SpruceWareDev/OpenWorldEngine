package dev.spruce.game.input;

public interface IKeyInput {

    void onKeyPress(int keyCode);

    void onKeyRelease(int keyCode);

    void onKeyTyped(int keyCode);
}
