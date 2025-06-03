package dev.spruce.game.util;

import com.raylib.Raylib;

import static com.raylib.Raylib.DrawTexturePro;

public class RenderUtils {

    // Pre-allocate these statically or reuse outside the draw call for even more gains
    private static final Raylib.Rectangle src = new Raylib.Rectangle();
    private static final Raylib.Rectangle dest = new Raylib.Rectangle();
    private static final Raylib.Vector2 origin = new Raylib.Vector2();

    public static void drawTextureScaled(Raylib.Texture texture, int posX, int posY, int targetWidth, int targetHeight, Raylib.Color tint) {
        src.x(0);
        src.y(0);
        src.width(texture.width());
        src.height(texture.height());

        dest.x(posX);
        dest.y(posY);
        dest.width(targetWidth);
        dest.height(targetHeight);
        
        origin.x(0);
        origin.y(0);

        DrawTexturePro(texture, src, dest, origin, 0.0f, tint);
    }
}
