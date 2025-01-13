import dev.spruce.game.Game;
import dev.spruce.game.assets.Assets;
import dev.spruce.game.sound.SoundManager;
import dev.spruce.game.sound.effect.AudioEffect;
import dev.spruce.game.sound.effect.impl.AdvancedReverbEffect;
import dev.spruce.game.sound.effect.impl.ReverbEffect;

import javax.sound.sampled.AudioFormat;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
