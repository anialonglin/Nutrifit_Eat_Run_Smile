package application;

import dataAccess.userData.meal;
import dataAccess.userData.mealData;
import java.util.Date;

public class mealManager {
    public static int addMeal(int ProfileId, String item, Date date){
        meal meal = new meal(ProfileId, date,item);
        return mealData.addMeal(meal);
    }
    public static void deleteMeal(int mealId){
        mealData.deleteMeal(mealId);
    }

    public static meal getMeal(int mealId){
        return mealData.getMeal(mealId);
    }

}
