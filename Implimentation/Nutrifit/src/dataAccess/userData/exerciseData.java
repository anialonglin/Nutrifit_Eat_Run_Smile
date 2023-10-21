package dataAccess.userData;

import java.util.ArrayList;

public class exerciseData {
    static userDatabase database = userDatabase.getInstance(); //this will be replaced by the SQL database for future deliverables
    public static int addExercise(exercise exercise){
         exercise.exerciseId=database.exerciseLog.size();
        database.exerciseLog.add(exercise);
        return exercise.exerciseId;
    }
    public static void deleteExercise(int exerciseId){
       // if(database.exerciseLog.get(exerciseId).profileId==profileId){//only delete if it is the right profile's exercise
            database.exerciseLog.remove(exerciseId);
    }

    public static exercise getExercise(int exerciseId){
        return database.exerciseLog.get(exerciseId);
    }
    public static ArrayList<exercise> getExercises(int profileID) {
        ArrayList<exercise> result = new ArrayList<exercise>();
        for(int i=0; i<database.exerciseLog.size(); i++){
            if(database.exerciseLog.get(i).profileId == profileID){
                result.add(database.exerciseLog.get(i));
            }
        }
        return result;
    }
}
