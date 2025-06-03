package dev.spruce.game.util;

import com.raylib.Raylib;

import static com.raylib.Raylib.DrawTexturePro;

public class RenderUtils {

    public static void drawTextureScaled(Raylib.Texture texture, int posX, int posY, int targetWidth, int targetHeight, Raylib.Color tint) {
        Raylib.Rectangle src = new Raylib.Rectangle();
        src.x(0);
        src.y(0);
        src.width(texture.width());
        src.height(texture.height());

        Raylib.Rectangle dest = new Raylib.Rectangle();
        dest.x(posX);
        dest.y(posY);
        dest.width(targetWidth);
        dest.height(targetHeight);

        Raylib.Vector2 origin = new Raylib.Vector2();
        origin.x(0);
        origin.y(0);

        DrawTexturePro(texture, src, dest, origin, 0.0f, tint);
    }
}
