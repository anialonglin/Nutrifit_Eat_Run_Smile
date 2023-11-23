package application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class foodManager {
    public static void addMeal(String username, String date, String mealType, String foodItem, int quantity) {
        dataAccess.UserProfile.database.getInstance().insertDietLog(username, date, mealType, foodItem, dataAccess.nutritionData.database.getInstance().getID(foodItem), quantity);
    }

    public static void reloadDatabase() {
        dataAccess.nutritionData.database.getInstance().loadDatabase();
    }

    public static ArrayList<String> queryFoodItem(String query) {
        String[] queryParams = query.split(" ");
        return dataAccess.nutritionData.database.getInstance().findFoodItem(queryParams);
    }

    public static double[] getAlignment(String username) {
        HashMap<String, Integer> foodIDs = getFoodIDs(username);
        return getPercentages(foodIDs);
    }

    public static double[] getAlignment(String username, int days) {
        //get foodIDs for the last int days
        HashMap<String, Integer> foodIDs = null;
        try {
            foodIDs = getFoodIDs(username, days);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return getPercentages(foodIDs);
    }

    private static double[] getPercentages(HashMap<String, Integer> foodIDs) {
        int[] categories = new int[4];
        categories[0] = dataAccess.nutritionData.database.getInstance().getProteinCount(foodIDs);
        categories[1] = dataAccess.nutritionData.database.getInstance().getCarbCount(foodIDs);
        categories[2] = dataAccess.nutritionData.database.getInstance().getFruitAndVegCount(foodIDs);
        categories[3] = foodIDs.size() - categories[0] - categories[1] - categories[2];
        double[] categoriesPercentages = new double[4];
        int total = categories[0] + categories[1] + categories[2] + categories[3];
        categoriesPercentages[0] = (double) categories[0] / total;
        categoriesPercentages[1] = (double) categories[1] / total;
        categoriesPercentages[2] = (double) categories[2] / total;
        categoriesPercentages[3] = (double) categories[3] / total;
        return categoriesPercentages;
    }

    private static HashMap<String, Integer> getFoodIDs(String username) {
        return dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
    }
    private static HashMap<String, Integer> getFoodIDs(String username, int days) throws ParseException {
        return dataAccess.UserProfile.database.getInstance().getFoodIDs(username, days);
    }

    public static double avgCalories(String username) {
        HashMap<String, Integer> foodIDs = dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
        double dailyAverage = 0;
        for (String date : foodIDs.keySet()) {
            dailyAverage += dataAccess.UserProfile.database.getInstance().dailyIntake(username, date);
        }
        return dailyAverage/foodIDs.keySet().size();
    }


}
