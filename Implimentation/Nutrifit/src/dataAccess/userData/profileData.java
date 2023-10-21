package dataAccess.userData;

public class profileData {
    static userDatabase database = userDatabase.getInstance(); //this will be replaced by the SQL database for future deliverables

    public static profile getProfile(int id) {
        return database.profiles.get(id);
    }

    public static void updateProfile(profile profile) {
        deleteProfile(profile.id);
        database.profiles.add(profile.id, profile);
    }

    public static void deleteProfile(int id) {
        database.profiles.remove(id);
    }

    public static int createProfile(profile profile) {
        profile.id=database.profiles.size();
        database.profiles.add(profile);
        return profile.id;
    }
}
