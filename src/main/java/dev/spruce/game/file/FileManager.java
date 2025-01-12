package dev.spruce.game.file;

import dev.spruce.game.entity.Entity;
import dev.spruce.game.state.impl.GameState;
import dev.spruce.game.world.Map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileManager {

    public static final String GAME_DATA_DIRECTORY = "gamedata";
    public static final String SAVES_DIRECTORY = GAME_DATA_DIRECTORY + File.separator + "saves";

    public static void checkDirectories() throws IOException {
        File dataDirectory = new File(GAME_DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File savesDirectory = new File(SAVES_DIRECTORY);
        if (!savesDirectory.exists()) {
            savesDirectory.mkdir();
        }
    }

    public static List<String> getGameNames() {
        File savesDirectory = new File(SAVES_DIRECTORY);
        return List.of(Objects.requireNonNull(savesDirectory.list()));
    }

    public static void saveGame(String name, GameState gameState) throws IOException {
        // Make new folder for world
        File saveFolder = new File(SAVES_DIRECTORY + File.separator + name);
        if (!saveFolder.exists()) {
            if (!saveFolder.mkdir()) {
                throw new RuntimeException("Failed to create new world directory!");
            }
        } else {
            // Remove old files
            for (String fileName : Objects.requireNonNull(saveFolder.list())) {
                File file = new File(saveFolder.getPath() + fileName);
                file.delete();
            }
        }

        // Save game world
        File mapFile = new File(saveFolder.getPath() + File.separator + "overworld");
        FileOutputStream mapOutputStream = new FileOutputStream(mapFile);
        ObjectOutputStream out = new ObjectOutputStream(mapOutputStream);
        out.writeObject(gameState.getMap());
        out.close();
        mapOutputStream.close();

        // Save entities
        int id = 0;
        for (Entity entity : gameState.getEntityManager().getEntities()) {
            File entityFile = new File(saveFolder.getPath() + File.separator + "e" + id);
            FileOutputStream entityOutputStream = new FileOutputStream(entityFile);
            out = new ObjectOutputStream(entityOutputStream);
            out.writeObject(entity);
            out.close();
            entityOutputStream.close();
            id++;
        }
    }

    // Methods for loading game.

    public static List<Entity> loadEntities(String name) throws IOException, ClassNotFoundException {
        List<Entity> entities = new ArrayList<>();
        File gameFolder = new File(String.format("%s%s%s", SAVES_DIRECTORY, File.separator, name));

        for (String fileName : Objects.requireNonNull(gameFolder.list())) {
            if (!fileName.startsWith("e")) {
                continue;
            }
            File entityFile = new File(gameFolder.getPath() + File.separator + fileName);
            FileInputStream entityInputStream = new FileInputStream(entityFile);
            ObjectInputStream in = new ObjectInputStream(entityInputStream);
            entities.add((Entity) in.readObject());
            in.close();
            entityInputStream.close();
        }
        return entities;
    }

    public static Map loadMap(String name) throws IOException, ClassNotFoundException {
        Map map;
        File gameFolder = new File(String.format("%s%s%s", SAVES_DIRECTORY, File.separator, name));

        File mapFile = new File(gameFolder.getPath() + File.separator + "overworld");
        FileInputStream mapInputStream = new FileInputStream(mapFile);
        ObjectInputStream in = new ObjectInputStream(mapInputStream);
        map = (Map) in.readObject();
        in.close();
        mapInputStream.close();
        return map;
    }
}
