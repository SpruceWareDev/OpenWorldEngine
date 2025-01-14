package dev.spruce.game.sound;

import dev.spruce.game.assets.Assets;
import dev.spruce.game.sound.effect.AudioEffect;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements Runnable {

    private String soundName;
    private AudioEffect[] effects;

    public SoundPlayer(String soundName, AudioEffect... effects) {
        this.soundName = soundName;
        this.effects = effects;
    }

    @Override
    public void run() {
        try {
            String filePath = Assets.getInstance().getSounds().getAsset(soundName);
            File audioFile = new File(filePath);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(format);
            sourceLine.start();

            byte[] audioBuffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = audioStream.read(audioBuffer, 0, audioBuffer.length)) != -1) {
                // Apply each effect in the list
                for (AudioEffect effect : effects) {
                    effect.process(audioBuffer, format.getSampleSizeInBits() / 8, format.getChannels(), format);
                }

                // Write the processed audio data to the source line
                sourceLine.write(audioBuffer, 0, bytesRead);
            }

            // Clean up
            sourceLine.drain();
            sourceLine.close();
            audioStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
