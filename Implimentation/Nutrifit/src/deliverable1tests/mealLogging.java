//package deliverable1tests;
//
//import application.mealManager;
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
//        int mealId = mealManager.addMeal(profileId, "meal1", new Date(2023, 1, 1));
//        assertEquals(mealManager.getMeal(mealId).profileId, profileId);
//        assertEquals(mealManager.getMeal(mealId).name, "meal1");
//        assertEquals(mealManager.getMeal(mealId).date, new Date(2023, 1, 1));
//
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)//will only pass on exception
//    public void deleteMeal() {
//
//        int profileId = userManager.createUserProfile("John", 35, true, 180, 180);
//        int mealId = mealManager.addMeal(profileId, "meal1", new Date(2023, 1, 1));
//        mealManager.deleteMeal(mealId);
//        assertEquals(mealManager.getMeal(mealId).profileId, profileId);
//    }
//
//}
