package dataAccess.userData;

public class exerciseData {
    static userDatabase database = userDatabase.getInstance(); //this will be replaced by the SQL database for future deliverables
    static int addExercise(exercise exercise){
        int exerciseId = database.exerciseLog.size();
        database.exerciseLog.add(exercise);
        return exercise.exerciseId;
    }
    static void deleteExercise(int profileId, int exerciseId){
        if(database.exerciseLog.get(exerciseId).profileId==profileId){//only delete if it is the right profile's exercise
            database.exerciseLog.remove(exerciseId);
        }
    }
}
