package application;

import java.util.ArrayList;

public class foodManager {
    public static void addMeal(String username, String date, String mealType, String foodItem, int quantity) {
        dataAccess.UserProfile.database.getInstance().insertDietLog(username, date, mealType, foodItem, quantity);
    }

    public static void reloadDatabase() {
        dataAccess.nutritionData.database.getInstance().loadDatabase();
    }

    public static ArrayList<String> queryFoodItem(String query) {
        String[] queryParams = query.split(" ");
        return dataAccess.nutritionData.database.getInstance().findFoodItem(queryParams);
    }
}
