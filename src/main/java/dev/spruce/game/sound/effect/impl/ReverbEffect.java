package dev.spruce.game.sound.effect.impl;

import dev.spruce.game.sound.effect.AudioEffect;

import javax.sound.sampled.AudioFormat;

public class ReverbEffect implements AudioEffect {
    private final int delayInSamples;
    private final float decay;
    private float[] delayBuffer;
    private int delayBufferIndex = 0;

    public ReverbEffect(int delayMs, float decay, AudioFormat format) {
        int sampleRate = (int) format.getSampleRate();
        int channels = format.getChannels();
        this.delayInSamples = (delayMs * sampleRate * channels) / 1000;
        this.decay = decay;
        this.delayBuffer = new float[delayInSamples];
    }

    @Override
    public void process(byte[] audioBuffer, int bytesPerSample, int channels, AudioFormat format) {
        for (int i = 0; i < audioBuffer.length; i += bytesPerSample) {
            int sample = 0;
            if (bytesPerSample == 2) { // 16-bit audio
                sample = (short) ((audioBuffer[i + 1] << 8) | (audioBuffer[i] & 0xFF));
            }

            // Apply reverb
            float delayedSample = delayBuffer[delayBufferIndex];
            float processedSample = sample + delayedSample * decay;

            // Store in delay buffer
            delayBuffer[delayBufferIndex] = processedSample;
            delayBufferIndex = (delayBufferIndex + 1) % delayBuffer.length;

            // Convert back to bytes
            int outputSample = (int) Math.max(Math.min(processedSample, Short.MAX_VALUE), Short.MIN_VALUE);
            if (bytesPerSample == 2) {
                audioBuffer[i] = (byte) (outputSample & 0xFF);
                audioBuffer[i + 1] = (byte) ((outputSample >> 8) & 0xFF);
            }
        }
    }
}