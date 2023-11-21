package application;

import java.util.HashMap;
import dataAccess.nutritionData.database;
public class nutritionDataManager {
    public static HashMap listFoods(){
        return database.getInstance().listFoods();
    }

    public static void reloadDatabase(){
        database.getInstance().loadDatabase();
    }
}
