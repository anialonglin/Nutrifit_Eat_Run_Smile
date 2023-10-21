package deliverable1tests;

import application.exerciseManager;
import application.userManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class exerciseLogging {
    @Test
    public void createExercise() { //creating an exercise.
        userManager userManager = new userManager();
        exerciseManager exerciseManager = new exerciseManager();
        int profileId = userManager.createUserProfile("John", 35, true, 180, 180);
        int exerciseId = exerciseManager.addExercise(profileId, "exercise1", 10, 2);
        assertEquals(exerciseManager.getExercise(exerciseId).name, "exercise1");
        assertEquals(exerciseManager.getExercise(exerciseId).duration, 10);
        assertEquals(exerciseManager.getExercise(exerciseId).intensity, 2);
    }

    @Test
    public void createExercises() { //creating exercises for multiple profiles.
        userManager userManager = new userManager();
        exerciseManager exerciseManager = new exerciseManager();
        int profileId1 = userManager.createUserProfile("John", 35, true, 180, 180);
        int profileId2 = userManager.createUserProfile("Jane", 30, false, 100, 100);
        int exerciseId1 = exerciseManager.addExercise(profileId1, "exercise1", 10, 2);
        int exerciseId2 = exerciseManager.addExercise(profileId2, "exercise2", 10, 2);
        int exerciseId3 = exerciseManager.addExercise(profileId1, "exercise3", 10, 2);
        assertEquals(exerciseManager.getExercise(exerciseId1).profileId,profileId1);
        assertEquals(exerciseManager.getExercise(exerciseId2).profileId,profileId2);
        assertEquals(exerciseManager.getExercise(exerciseId3).profileId,profileId1);

    }

    @Test(expected = IndexOutOfBoundsException.class)//will only pass on exception
    public void deleteExercise() { //makes sure deleting works
        userManager userManager = new userManager();
        exerciseManager exerciseManager = new exerciseManager();
        int profileId1 = userManager.createUserProfile("John", 35, true, 180, 180);
        int exerciseId1 = exerciseManager.addExercise(profileId1, "exercise1", 10, 2);
        exerciseManager.deleteExercise(exerciseId1);
        exerciseManager.getExercise(exerciseId1);
    }

}
