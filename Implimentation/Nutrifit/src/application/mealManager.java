package application;


import java.util.Date;
import dataAccess.UserProfile.Database;
public class mealManager {
   public static void addProfile( String name, int age, String sex, double height_cm, double weight_kg){
       
       Database.getInstance().insertUserProfile(name,age,sex,height_cm,weight_kg);
   }
//
//    public static int addMeal(int profileId, String meal1, Date date) {
//    }
}
