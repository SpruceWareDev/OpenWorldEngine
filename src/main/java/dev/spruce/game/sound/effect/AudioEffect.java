package dev.spruce.game.sound.effect;

import javax.sound.sampled.AudioFormat;

public interface AudioEffect {
    /**
     * Process a chunk of audio data.
     * @param audioBuffer The audio data to process.
     * @param bytesPerSample The number of bytes per audio sample (e.g., 2 for 16-bit audio).
     * @param channels The number of audio channels (e.g., 1 for mono, 2 for stereo).
     * @param format The audio format (for additional context if needed).
     */
    void process(byte[] audioBuffer, int bytesPerSample, int channels, AudioFormat format);
}