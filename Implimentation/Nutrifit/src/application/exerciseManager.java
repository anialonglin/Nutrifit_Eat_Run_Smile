package application;

import java.util.ArrayList;

public class exerciseManager {
    public static int addExercise(String username, String date, String exerciseType, int duration, int intensity) {
        return dataAccess.UserProfile.database.getInstance().insertExerciseLog(username, date, exerciseType, duration, intensity);
    }

    public static ArrayList<String> getExercise(String username, int exerciseId) {
        return dataAccess.UserProfile.database.getInstance().getExercise(username, exerciseId);
    }

    public static void deleteExercise(String username, int exerciseId) {
        dataAccess.UserProfile.database.getInstance().deleteExercise(username, exerciseId);
    }
}
