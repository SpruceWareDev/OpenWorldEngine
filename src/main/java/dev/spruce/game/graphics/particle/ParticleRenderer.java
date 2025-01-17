package dev.spruce.game.graphics.particle;

import dev.spruce.game.graphics.Camera;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParticleRenderer {

    private CopyOnWriteArrayList<Particle> particles = new CopyOnWriteArrayList<>();

    public void spawnParticle(float x, float y, float size, int lifetimeTicks, Particle.ParticleType type, Color color) {
        particles.add(new Particle(x, y, size, lifetimeTicks, type, color));
    }

    public void update(double delta) {
        for (Particle particle : particles) {
            particle.update(delta);
            if (particle.isDead()) {
                particles.remove(particle);
            }
        }
    }

    public void render(Graphics graphics, Camera camera) {
        for (Particle particle : particles) {
            particle.render(graphics, camera);
        }
    }
}
