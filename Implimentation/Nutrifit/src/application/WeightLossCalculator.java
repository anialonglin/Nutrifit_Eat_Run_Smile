package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeightLossCalculator {

    public static Double BMRcalc(String username) {
        ArrayList<String> user = dataAccess.UserProfile.database.getInstance().getUserProfile(username);
        double BMR = 0;
        switch (user.get(2)) {
            case "male":
            case "Male":
            case "M":
            case "m":
                BMR = 66.47 + (13.75 * Double.parseDouble(user.get(4))) + (5.003 * Double.parseDouble(user.get(3))) - (6.755 * Double.parseDouble(user.get(1)));
                break;
            case "female":
            case "Female":
            case "F":
            case "f":
                BMR = 655.1 + (9.563 * Double.parseDouble(user.get(4))) + (1.85 * Double.parseDouble(user.get(3))) - (4.676 * Double.parseDouble(user.get(1)));
                break;
        }
        return BMR;
    }

    public static double calculateWeightLoss(String username, Date futureDate) {
        // Calculate the net calorie deficit
        double netCalorieDeficit = exerciseManager.avgExercise(username) + BMRcalc(username) - application.foodManager.avgCalories(username);
        System.out.println("netCalorieDeficit: " + netCalorieDeficit);
        // Calculate the weight loss in kg (0.00013 is the conversion factor from kcalories to kg)
        return netCalorieDeficit * 0.00013;
    }

    private static long daysBetween(Date startDate, Date endDate) {
        long difference = endDate.getTime() - startDate.getTime();
        return difference / 86400000;
    }


}

