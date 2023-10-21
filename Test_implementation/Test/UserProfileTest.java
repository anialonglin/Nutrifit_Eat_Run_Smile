import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void testBMRMale() {
        // Test BMR calculation for a male user
        UserProfile user = new UserProfile(1, "John Doe", 30, 'M', 175.0, 70.5);

        // Expected BMR for a 30-year-old male with the given height and weight
        double expectedBMR = 88.362 + (13.397 * 70.5) + (4.799 * 175.0) - (5.677 * 30);

        assertEquals(expectedBMR, user.getBMR(), 0.01); // Using delta for double comparisons
    }

    @Test
    public void testBMRFemale() {
        // Test BMR calculation for a female user
        UserProfile user = new UserProfile(2, "Jane Smith", 25, 'F', 160.0, 55.0);

        // Expected BMR for a 25-year-old female with the given height and weight
        double expectedBMR = 447.593 + (9.247 * 55.0) + (3.098 * 160.0) - (4.330 * 25);

        assertEquals(expectedBMR, user.getBMR(), 0.01);
    }

    @Test
    public void testBMRInvalidSex() {
        // Test BMR calculation for a user with an invalid sex
        UserProfile user = new UserProfile(3, "Invalid User", 35, 'X', 170.0, 65.0);

        // BMR should be 0 for an invalid sex
        assertEquals(0.0, user.getBMR(), 0.01);
    }
}
