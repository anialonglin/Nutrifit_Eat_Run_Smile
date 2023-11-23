package application;

import java.util.ArrayList;

public class userManager {
    public static void createUserProfile(String name, int age, String sex, double height, double weight) {
        dataAccess.UserProfile.database.getInstance().insertUserProfile(name, age, sex, height, weight);
    }

    public static ArrayList<String> listProfiles() {
        return dataAccess.UserProfile.database.getInstance().listProfiles();
    }

    public static void deleteUserProfile(String username) {
        dataAccess.UserProfile.database.getInstance().deleteUserProfile(username);
    }

    public static ArrayList<String> getUserProfile(String username) {
        return dataAccess.UserProfile.database.getInstance().getUserProfile(username);
    }

    public static void setAge(String username, int newAge) {
        dataAccess.UserProfile.database.getInstance().setAge(username, newAge);
    }

    public static void setSex(String username, String text) {
        dataAccess.UserProfile.database.getInstance().setSex(username, text);
    }

    public static void setHeight(String username, double height) {
        dataAccess.UserProfile.database.getInstance().setHeight(username, height);

    }

    public static void setWeight(String username, double weight) {
        dataAccess.UserProfile.database.getInstance().setWeight(username, weight);
    }

    public static double getWeight(String username) {
        return dataAccess.UserProfile.database.getInstance().getWeight(username);
    }

}
