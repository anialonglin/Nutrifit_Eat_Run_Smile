package application;

import java.util.ArrayList;
import java.util.Date;

public class WeightLossCalculator {

    public static Double BMRcalc(String username) {
        ArrayList<String> user = dataAccess.UserProfile.database.getInstance().getUserProfile(username);
        double BMR = switch (user.get(2)) {
            case "male", "Male", "M", "m", "boy", "Boy", "B", "b" ->
                    66.47 + (13.75 * Double.parseDouble(user.get(4))) + (5.003 * Double.parseDouble(user.get(3))) - (6.755 * Double.parseDouble(user.get(1)));
            case "female", "Female", "F", "f", "girl", "Girl", "g", "G" ->
                    655.1 + (9.563 * Double.parseDouble(user.get(4))) + (1.85 * Double.parseDouble(user.get(3))) - (4.676 * Double.parseDouble(user.get(1)));
            default -> 0;
        };
        return BMR;
    }

    public static double calculateWeightLoss(String username, Date futureDate) {
        // Calculate the net calorie deficit
        double netCalorieDeficit = exerciseManager.avgExercise(username) + BMRcalc(username) - application.foodManager.avgCalories(username);
        System.out.println("exerciseManager.avgExercise(username): " + exerciseManager.avgExercise(username) + "\n BMRcalc(username): " + BMRcalc(username) + "\n application.foodManager.avgCalories(username): " + application.foodManager.avgCalories(username));
        System.out.println("netCalorieDeficit: " + netCalorieDeficit);
        // Calculate the weight loss in kg (0.00013 is the conversion factor from kcalories to kg)
        return netCalorieDeficit * 0.00013 * daysBetween(new Date(), futureDate);
    }

    private static long daysBetween(Date startDate, Date endDate) {
        //get days between two dates
        long difference = endDate.getTime() - startDate.getTime();
        return difference / 86400000;
    }


}

