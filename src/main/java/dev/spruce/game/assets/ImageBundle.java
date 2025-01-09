package dev.spruce.game.assets;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImageBundle {

    private final List<BufferedImage> images;

    public ImageBundle(BufferedImage... imagesIn) {
        images = new ArrayList<>();
        images.addAll(Arrays.stream(imagesIn).toList());
    }

    public Optional<BufferedImage> getSingle() {
        if (images.isEmpty()) return Optional.empty();
        return Optional.of(images.get(0));
    }

    public List<BufferedImage> getImages() {
        return images;
    }
}
