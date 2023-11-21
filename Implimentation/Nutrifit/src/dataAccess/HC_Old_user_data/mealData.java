package dataAccess.HC_Old_user_data;

public class mealData {
    static userDatabase database = userDatabase.getInstance();
    public static int addMeal(meal meal) {
        meal.mealId=database.mealLog.size();
        database.mealLog.add(meal);
        return meal.mealId;
    }

    public static void deleteMeal(int mealId){
        database.mealLog.remove(mealId);
    }

    public static meal getMeal(int mealId) {
        return database.mealLog.get(mealId);
    }
}
