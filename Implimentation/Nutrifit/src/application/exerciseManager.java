package application;

import java.util.ArrayList;
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

    public static double calorieBurned(String username, int exerciseID){
        switch (dataAccess.UserProfile.database.getInstance().getExerciseIntensity(exerciseID)){
            case 1:
                return (dataAccess.UserProfile.database.getInstance().getExerciseDuration(exerciseID)*2*dataAccess.UserProfile.database.getInstance().getWeight(username))/200;
            case 2:
                return (dataAccess.UserProfile.database.getInstance().getExerciseDuration(exerciseID)*5*dataAccess.UserProfile.database.getInstance().getWeight(username))/200;
            case 3:
                return (dataAccess.UserProfile.database.getInstance().getExerciseDuration(exerciseID)*8*dataAccess.UserProfile.database.getInstance().getWeight(username))/200;
            case 4:
                return (dataAccess.UserProfile.database.getInstance().getExerciseDuration(exerciseID)*11*dataAccess.UserProfile.database.getInstance().getWeight(username))/200;
        }
        return -1;
    }

    public static double avgExercise(String username) {
       //select NutrientValue from nutrientAmount where FoodID = FoodID and NutrientID = 208
        HashMap<String, Integer> exerciseIDs = dataAccess.UserProfile.database.getInstance().getExerciseIDs(username);
        double average = 0;

    }
}
