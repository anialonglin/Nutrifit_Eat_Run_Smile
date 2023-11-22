package application;

import java.util.ArrayList;

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
        ArrayList<Integer> foodIDs = getFoodIDs(username);
        System.out.println(foodIDs);
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

    private static ArrayList<Integer> getFoodIDs(String username) {
        return dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
    }

    public static double avgCalories(String username) {
        double average = 0;
        ArrayList<Integer> foodIDs = getFoodIDs(username);
        for (int i = 0; i < foodIDs.size(); i++) {
            average += dataAccess.nutritionData.database.getInstance().getCalories(foodIDs.get(i));
        }
        average /= foodIDs.size();
        return average;
    }
}
