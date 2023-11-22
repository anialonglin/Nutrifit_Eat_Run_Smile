//package deliverable1tests;
//
//import application.foodManager;
//import application.userManager;
//import org.junit.Test;
//
//import java.util.Date;
//
//import static org.junit.Assert.assertEquals;
//
//public class mealLogging {
//    @Test
//    public void addMeal() {
//
//        int profileId = userManager.createUserProfile("John", 35, true, 180, 180);
//        int mealId = foodManager.addMeal(profileId, "meal1", new Date(2023, 1, 1));
//        assertEquals(foodManager.getMeal(mealId).profileId, profileId);
//        assertEquals(foodManager.getMeal(mealId).name, "meal1");
//        assertEquals(foodManager.getMeal(mealId).date, new Date(2023, 1, 1));
//
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)//will only pass on exception
//    public void deleteMeal() {
//
//        int profileId = userManager.createUserProfile("John", 35, true, 180, 180);
//        int mealId = foodManager.addMeal(profileId, "meal1", new Date(2023, 1, 1));
//        foodManager.deleteMeal(mealId);
//        assertEquals(foodManager.getMeal(mealId).profileId, profileId);
//    }
//
//}
