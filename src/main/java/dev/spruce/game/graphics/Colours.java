package dev.spruce.game.graphics;

import com.raylib.Raylib;

import java.awt.*;

public class Colours {

    public static final Color MINDARO = new Color(0xBCE784);
    public static final Color EMERALD = new Color(0x5DD39E);
    public static final Color BLUE = new Color(0x348AA7);
    public static final Color ULTRA_VIOLET = new Color(0x525174);
    public static final Color ENGLISH_VIOLET = new Color(0x513B56);

    public static final Raylib.Color FIREBALL_PARTICLE = awtToRay(Color.orange);

    public static Raylib.Color awtToRay(Color colorIn) {
        return new Raylib.Color()
                .r((byte) colorIn.getRed())
                .g((byte) colorIn.getGreen())
                .b((byte) colorIn.getBlue())
                .a((byte) colorIn.getAlpha());
    }
}
