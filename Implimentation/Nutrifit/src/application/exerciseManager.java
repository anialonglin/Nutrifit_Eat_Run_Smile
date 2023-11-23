package application;

import java.util.HashMap;

public class exerciseManager {
    public static int addExercise(String username, String date, String exerciseType, int duration, String intensity) {
        int intensityint = -1;
        switch (intensity) {
            case "Low":
                intensityint = 1;
                break;
            case "Medium":
                intensityint = 2;
                break;
            case "High":
                intensityint = 3;
                break;
            case "Very High":
                intensityint = 4;
                break;
        }
        return dataAccess.UserProfile.database.getInstance().insertExerciseLog(username, date, exerciseType, duration, intensityint);
    }



    public static double avgExercise(String username) {
        HashMap<String, Integer> exerciseIDs = dataAccess.UserProfile.database.getInstance().getExerciseIDs(username);
        double average = 0;
        //get average calories burned per day
        for (String date : exerciseIDs.keySet()) {
            average += dataAccess.UserProfile.database.getInstance().dailyBurn(username, date);
        }
        return average/exerciseIDs.keySet().size();
    }
}
