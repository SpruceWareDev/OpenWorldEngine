package dev.spruce.game.assets;

/*
    Class that is inherited by other classes to manage a specific asset type.
    I, is the type of key used to identify that asset type (e.g. int, String, ...)
    T, is the type of asset (e.g. BufferedImage, String, ...)
 */
public abstract class AssetManager<I, T> {
    public abstract T getAsset(I assetId);
}
