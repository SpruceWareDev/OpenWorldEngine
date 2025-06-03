package dev.spruce.game.graphics.particle;

import dev.spruce.game.graphics.Camera;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParticleRenderer {

    private static final int MAX_PARTICLES = 1000;

    private final CopyOnWriteArrayList<Particle> particles = new CopyOnWriteArrayList<>();

    public void spawnParticle(float x, float y, float size, int lifetimeTicks, Particle.ParticleType type, Color color) {
        particles.add(new Particle(x, y, size, lifetimeTicks, type, color));
    }

    public void spawnParticle(Particle particle) {
        particles.add(particle);
    }

    public void update(double delta) {
        if (particles.size() >= MAX_PARTICLES) {
            particles.subList(0, particles.size() - MAX_PARTICLES).clear();
        }

        for (Particle particle : particles) {
            particle.update(delta);
            if (particle.isDead()) {
                particles.remove(particle);
            }
        }
    }

    public void render(Camera camera) {
        for (Particle particle : particles) {
            particle.render(camera);
        }
    }

    public int getParticleCount() {
        return particles.size();
    }
}
