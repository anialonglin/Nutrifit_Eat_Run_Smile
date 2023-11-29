package application;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class foodManager {
    public static void addMeal(String username, String date, String mealType, String foodItem, int quantity) {
        //convert dd-MM-yyyy date to sql date
        java.util.Date utilDate = null;
        try {
            utilDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        dataAccess.UserProfile.database.getInstance().insertDietLog(username, sqlDate, mealType, foodItem, dataAccess.nutritionData.database.getInstance().getID(foodItem), quantity);
    }

    public static void reloadDatabase() {
        dataAccess.nutritionData.database.getInstance().loadDatabase();
    }

    public static ArrayList<String> queryFoodItem(String query) {
        String[] queryParams = query.split(" ");
        return dataAccess.nutritionData.database.getInstance().findFoodItem(queryParams);
    }

    public static double[] getAlignment(String username) {
        HashMap<Date, ArrayList<Integer>> foodIDs = dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
        System.out.println("foodIDs in getAllignment: " + foodIDs);
        return getPercentages(foodIDs);
    }

    public static double[] getAlignment(String username, int days) {
        //get foodIDs for the last int days
        HashMap<Date, ArrayList<Integer>> foodIDs = dataAccess.UserProfile.database.getInstance().getFoodIDs(username, days);
        return getPercentages(foodIDs);
    }

    private static double[] getPercentages(HashMap<Date, ArrayList<Integer>> foodIDs) {
        int[] categories = dataAccess.nutritionData.database.getInstance().getFoodGroups(foodIDs);
        double total = categories[0] + categories[1] + categories[2] + categories[3];
        double[] categoriesPercentages = new double[4];
        System.out.println("total: " + total + "\ncategories[0]: " + categories[0] + "\ncategories[1]: " + categories[1] + "\ncategories[2]: " + categories[2] + "\ncategories[3]: " + categories[3]);
        categoriesPercentages[0] = (double) categories[0] / total;
        categoriesPercentages[1] = (double) categories[1] / total;
        categoriesPercentages[2] = (double) categories[2] / total;
        categoriesPercentages[3] = (double) categories[3] / total;
        return categoriesPercentages;
    }

    private static HashMap<Date, ArrayList<Integer>> getFoodIDs(String username) {
        return dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
    }

    private static HashMap<Date, ArrayList<Integer>> getFoodIDs(String username, int days) {
        return dataAccess.UserProfile.database.getInstance().getFoodIDs(username, days);
    }

    public static double avgCalories(String username) {
        HashMap<Date, ArrayList<Integer>> foodIDs = dataAccess.UserProfile.database.getInstance().getFoodIDs(username);
        double dailyAverage = 0;
        for (Date date : foodIDs.keySet()) {
            dailyAverage += dataAccess.UserProfile.database.getInstance().dailyIntake(username, date);
        }
        return dailyAverage / foodIDs.keySet().size();
    }

}
