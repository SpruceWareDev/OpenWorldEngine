package dev.spruce.game.file;

import dev.spruce.game.state.impl.GameState;

import java.io.*;

public class FileManager {

    public static final String SAVE_DIRECTORY = "gamedata";
    public static final String SAVES_DIRECTORY = SAVE_DIRECTORY + File.separator + "saves";

    public static void checkDirectories() throws IOException {
        File dataDirectory = new File(SAVE_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File savesDirectory = new File(SAVES_DIRECTORY);
        if (!savesDirectory.exists()) {
            savesDirectory.mkdir();
        }
    }

    public static void saveGame(String name, GameState gameState) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(String.format("%s%s%s", SAVES_DIRECTORY, File.separator, name));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(gameState);
        out.close();
        fileOut.close();
    }

    public static GameState loadGame(String name) throws IOException, ClassNotFoundException {
        GameState gameState;

        FileInputStream fileIn = new FileInputStream(String.format("%s%s%s", SAVES_DIRECTORY, File.separator, name));
        ObjectInputStream in = new ObjectInputStream(fileIn);

        gameState = (GameState) in.readObject();

        in.close();
        fileIn.close();

        if (gameState == null) {
            throw new RuntimeException("Failed to load game: " + name);
        }

        return gameState;
    }
}
