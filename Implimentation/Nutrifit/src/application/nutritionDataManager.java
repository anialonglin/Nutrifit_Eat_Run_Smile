package application;

import java.util.HashMap;

import dataAccess.nutritionData.database;

public class nutritionDataManager {
    public static HashMap<Integer, String> listFoods() {
        return database.getInstance().listFoods();
    }

    public static void reloadDatabase() {
        database.getInstance().loadDatabase();
    }

    public static HashMap<String, String> itemNutrition(int id) {
        return database.getInstance().itemNutrition(id);
    }
}
