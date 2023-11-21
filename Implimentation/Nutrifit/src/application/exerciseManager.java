package application;

import dataAccess.HC_Old_user_data.exercise;
import dataAccess.HC_Old_user_data.exerciseData;

public class exerciseManager {
    public static int addExercise(int profileId, String name, int duration,int intensity){
        exercise exercise = new exercise(profileId, name, duration, intensity);
        return exerciseData.addExercise(exercise);
    }

    public static void deleteExercise(int exerciseId){
        exerciseData.deleteExercise(exerciseId);
    }

    public static exercise getExercise(int exerciseId){
        return exerciseData.getExercise(exerciseId);
    }
}
