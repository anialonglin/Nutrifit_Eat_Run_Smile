package application;

import java.sql.Date;
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
        //convert dd-MM-yyyy date to sql date
        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(date);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return dataAccess.UserProfile.database.getInstance().insertExerciseLog(username, sqlDate, exerciseType, duration, intensityint);
    }

    public static double avgExercise(String username) {
        HashMap<Date, Integer> exerciseIDs = dataAccess.UserProfile.database.getInstance().getExerciseIDs(username);
        //loop through days and get average exercise
        double total = 0;
        for (Date date : exerciseIDs.keySet()) {
            total += dataAccess.UserProfile.database.getInstance().dailyBurn(username, date);
        }
        System.out.println("avgExercise:"+(total / exerciseIDs.keySet().size()));
        return total / exerciseIDs.keySet().size();
    }
}
