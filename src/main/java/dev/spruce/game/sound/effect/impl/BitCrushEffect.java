package dev.spruce.game.sound.effect.impl;

import dev.spruce.game.sound.effect.AudioEffect;

import javax.sound.sampled.AudioFormat;

public class BitCrushEffect implements AudioEffect {

    private final int bitDepth; // Target bit depth (e.g., 8, 4, etc.)
    private final int downSampleFactor; // Factor to reduce the sampling rate

    public BitCrushEffect(int bitDepth, int downSampleFactor) {
        this.bitDepth = bitDepth;
        this.downSampleFactor = downSampleFactor;
    }

    @Override
    public void process(byte[] audioBuffer, int bytesPerSample, int channels, AudioFormat format) {
        int maxValue = (1 << (bitDepth - 1)) - 1; // Maximum value for the target bit depth
        int minValue = -maxValue - 1; // Minimum value for the target bit depth

        int sampleCount = 0; // Counter to track downsampling

        for (int i = 0; i < audioBuffer.length; i += bytesPerSample) {
            // Read the current sample
            int sample = 0;
            if (bytesPerSample == 2) { // 16-bit audio
                sample = (short) ((audioBuffer[i + 1] << 8) | (audioBuffer[i] & 0xFF));
            }

            // Apply bit depth reduction
            sample = Math.round((float) sample / maxValue) * maxValue;
            sample = Math.max(Math.min(sample, maxValue), minValue); // Clamp to target range

            // Apply downsampling
            if (sampleCount % downSampleFactor != 0) {
                sample = 0; // Mute skipped samples
            }
            sampleCount++;

            // Convert back to bytes
            int outputSample = sample;
            if (bytesPerSample == 2) {
                audioBuffer[i] = (byte) (outputSample & 0xFF);
                audioBuffer[i + 1] = (byte) ((outputSample >> 8) & 0xFF);
            }
        }
    }
}