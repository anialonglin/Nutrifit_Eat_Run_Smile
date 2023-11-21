package application;


import dataAccess.UserProfile.database;

public class mealManager {
    public static void addMeal(String username, String date, String mealType, String foodItem, int quantity) {
        database.getInstance().insertDietLog(username, date, mealType, foodItem, quantity);
    }
}
