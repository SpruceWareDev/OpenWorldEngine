package dev.spruce.game.util;

public class DifficultyUtils {

    public static final String[] DIFFICULTY_NAMES = {
        "Easy",
        "Normal",
        "Hard",
        "Insane",
        "Terror",
        "Nightmare",
        "Hell"
    };

    public static String getDifficultyName(int difficulty) {
        if (difficulty < 0) {
            throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }
        if (difficulty >= DIFFICULTY_NAMES.length) {
            return DIFFICULTY_NAMES[DIFFICULTY_NAMES.length - 1];
        }
        return DIFFICULTY_NAMES[difficulty];
    }
}
