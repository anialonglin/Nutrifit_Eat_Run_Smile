package dataAccess.userData;

import java.util.ArrayList;

//this data will come from the SQL database in the final version


public class userDatabase {

    private static userDatabase INSTANCE;
    private userDatabase() {}
    public static userDatabase getInstance () {
        if(INSTANCE == null) {
            INSTANCE = new userDatabase();
        }
        return INSTANCE;
    }
    public ArrayList<profile> profiles = new ArrayList<profile>();
    public ArrayList<exercise> exerciseLog = new ArrayList<exercise>();
}