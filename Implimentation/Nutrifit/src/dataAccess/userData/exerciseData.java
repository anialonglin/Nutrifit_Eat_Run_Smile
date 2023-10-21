package dataAccess.userData;

public class exerciseData {
    static userDatabase database = userDatabase.getInstance(); //this will be replaced by the SQL database for future deliverables

    public static int addExercise(exercise exercise) {
        exercise.exerciseId = database.exerciseLog.size();
        database.exerciseLog.add(exercise);
        return exercise.exerciseId;
    }

    public static void deleteExercise(int exerciseId) {
        database.exerciseLog.remove(exerciseId);
    }

    public static exercise getExercise(int exerciseId) {
        return database.exerciseLog.get(exerciseId);
    }
}

