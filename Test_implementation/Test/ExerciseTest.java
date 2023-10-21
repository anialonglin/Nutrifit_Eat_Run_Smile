import static org.junit.Assert.*;
import org.junit.Test;

public class ExerciseTest {

    @Test
    public void testGetDuration() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals(30, exercise.getDuration());
    }

    @Test
    public void testGetBurnedCal() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals(exercise.caloryCount(), exercise.getBurnedCal());
    }

    @Test
    public void testGetType() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals("Running", exercise.getType());
    }

    @Test
    public void testGetIntensity() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals("High", exercise.getIntensity());
    }

    @Test
    public void testGetName() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals("Running", exercise.getName());
    }

    @Test
    public void testSetDuration() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        exercise.setDuration(45);
        assertEquals(45, exercise.getDuration());
    }

    @Test
    public void testSetBurnedCal() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        exercise.setBurnedCal(360);
        assertEquals(360, exercise.getBurnedCal());
    }

    @Test
    public void testSetType() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        exercise.setType("Walking");
        assertEquals("Walking", exercise.getType());
    }

    @Test
    public void testSetIntensity() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        exercise.setIntensity("Medium");
        assertEquals("Medium", exercise.getIntensity());
    }

    @Test
    public void testSetName() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        exercise.setName("Biking");
        assertEquals("Biking", exercise.getName());
    }

    @Test
    public void testCaloryCount() {
        Exercise exercise = new Exercise("Running", 30, "Running", "High");
        assertEquals(1007, exercise.caloryCount());
    }
}
