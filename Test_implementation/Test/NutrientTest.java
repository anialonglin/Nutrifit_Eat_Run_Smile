import org.junit.Test;
import static org.junit.Assert.assertEquals;

class NutritionTest {

    
    @Test
    public void testAddIngredient() {
        Nutrition nutrition = new Nutrition("Breakfast");
        Nutrient nutrient = new Nutrient(10.5, 25.3, 7.2);
        nutrition.addIngredient("Eggs", nutrient);
        assertEquals(nutrient, nutrition.getTotalNutrients());
    }

    @Test
    public void testTotalNutrients() {
        Nutrition nutrition = new Nutrition("Lunch");
        Nutrient nutrient1 = new Nutrient(7.0, 20.5, 5.5);
        Nutrient nutrient2 = new Nutrient(4.0, 15.2, 3.8);
        nutrition.addIngredient("Chicken", nutrient1);
        nutrition.addIngredient("Rice", nutrient2);
        Nutrient totalNutrients = nutrition.getTotalNutrients();
        assertEquals(11.0, totalNutrients.getProteins(), 0.01);
        assertEquals(35.7, totalNutrients.getCarbs(), 0.01);
        assertEquals(9.3, totalNutrients.getVitamins(), 0.01);
    }

    @Test
    public void testGetMealType() {
        Nutrition nutrition = new Nutrition("Dinner");
        assertEquals("Dinner", nutrition.getMealType());
    }
}

public class NutrientTest {

    @Test
    public void testGetProteins() {
        Nutrient nutrient = new Nutrient(10.5, 25.3, 7.2);
        assertEquals(10.5, nutrient.getProteins(), 0.01);
    }

    @Test
    public void testGetCarbs() {
        Nutrient nutrient = new Nutrient(10.5, 25.3, 7.2);
        assertEquals(25.3, nutrient.getCarbs(), 0.01);
    }

    @Test
    public void testGetVitamins() {
        Nutrient nutrient = new Nutrient(10.5, 25.3, 7.2);
        assertEquals(7.2, nutrient.getVitamins(), 0.01);
    }
}
