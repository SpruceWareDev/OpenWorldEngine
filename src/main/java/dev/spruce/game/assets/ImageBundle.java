package dev.spruce.game.assets;

import com.raylib.Raylib;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImageBundle {

    private final List<Raylib.Texture> images;

    public ImageBundle(Raylib.Texture... imagesIn) {
        images = new ArrayList<>();
        images.addAll(Arrays.stream(imagesIn).toList());
    }

    public Optional<Raylib.Texture> getSingle() {
        if (images.isEmpty()) return Optional.empty();
        return Optional.of(images.get(0));
    }

    public List<Raylib.Texture> getImages() {
        return images;
    }

    public void dispose() {
        for (Raylib.Texture texture : images) {
            Raylib.UnloadTexture(texture);
        }
    }
}
