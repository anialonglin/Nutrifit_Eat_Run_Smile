package application;

import java.sql.Date;
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
        try {
            //convert dd-MM-yyyy date to sql date

            return dataAccess.UserProfile.database.getInstance().insertExerciseLog(username,new Date(new java.text.SimpleDateFormat("dd-MM-yyyy").parse(date).getTime()) , exerciseType, duration, intensityint);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        System.out.println("someting went wrong");
        return 1;
    }

    public static double avgExercise(String username) {
        HashMap<Date, ArrayList<Integer>> exerciseIDs = dataAccess.UserProfile.database.getInstance().getExerciseIDs(username);
        //loop through days and get average exercise
        double total = 0;
        for (Date date : exerciseIDs.keySet()) {
            ArrayList<Integer> ids = exerciseIDs.get(date);
            for (int id : ids) {
                total += dataAccess.UserProfile.database.getInstance().calorieBurned(username, id);
            }
        }
        System.out.println("avgExercise:" + (total / exerciseIDs.keySet().size()));
        return total / exerciseIDs.keySet().size();
    }
}
