package dev.spruce.game.sound.effect.impl;

import dev.spruce.game.sound.effect.AudioEffect;

import javax.sound.sampled.AudioFormat;
import java.util.Arrays;

public class AdvancedReverbEffect implements AudioEffect {
    private final int[] delayInSamples; // Multiple delay times for taps
    private final float[] decayFactors; // Different decay for each tap
    private float[][] delayBuffers; // Separate buffers for each tap
    private int[] delayBufferIndices; // Indices for each buffer

    public AdvancedReverbEffect(int[] delayMs, float[] decayFactors, AudioFormat format) {
        int sampleRate = (int) format.getSampleRate();
        int channels = format.getChannels();

        // Initialize delay times and decay factors
        this.delayInSamples = new int[delayMs.length];
        for (int i = 0; i < delayMs.length; i++) {
            this.delayInSamples[i] = (delayMs[i] * sampleRate * channels) / 1000;
        }
        this.decayFactors = decayFactors;

        // Initialize delay buffers
        this.delayBuffers = new float[delayInSamples.length][];
        this.delayBufferIndices = new int[delayInSamples.length];
        for (int i = 0; i < delayInSamples.length; i++) {
            this.delayBuffers[i] = new float[delayInSamples[i]];
            Arrays.fill(this.delayBuffers[i], 0); // Initialize buffers with silence
        }
    }

    @Override
    public void process(byte[] audioBuffer, int bytesPerSample, int channels, AudioFormat format) {
        for (int i = 0; i < audioBuffer.length; i += bytesPerSample) {
            int sample = 0;
            if (bytesPerSample == 2) { // 16-bit audio
                sample = (short) ((audioBuffer[i + 1] << 8) | (audioBuffer[i] & 0xFF));
            }

            float outputSample = sample;

            // Process each delay tap
            for (int j = 0; j < delayBuffers.length; j++) {
                float delayedSample = delayBuffers[j][delayBufferIndices[j]];

                // Mix delayed sample with the current sample
                outputSample += delayedSample * decayFactors[j];

                // Store the new sample in the buffer with decay
                delayBuffers[j][delayBufferIndices[j]] = sample + delayedSample * decayFactors[j];

                // Increment the buffer index
                delayBufferIndices[j] = (delayBufferIndices[j] + 1) % delayBuffers[j].length;
            }

            // Prevent clipping
            outputSample = Math.max(Math.min(outputSample, Short.MAX_VALUE), Short.MIN_VALUE);

            // Convert back to bytes
            int outputSampleInt = (int) outputSample;
            if (bytesPerSample == 2) {
                audioBuffer[i] = (byte) (outputSampleInt & 0xFF);
                audioBuffer[i + 1] = (byte) ((outputSampleInt >> 8) & 0xFF);
            }
        }
    }
}