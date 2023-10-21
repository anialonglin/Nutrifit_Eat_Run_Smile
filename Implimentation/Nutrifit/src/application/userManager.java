package application;

import dataAccess.userData.profile;
import dataAccess.userData.profileData;

public class userManager {
    public int createUserProfile(String name, int age, boolean sex, double height, double weight) {
        profile profile = new profile(name,age,sex,height,weight);
        return profileData.createProfile(profile);
    }

    public profile getUserProfile(int id) {
        return profileData.getProfile(id);
    }

    public void updateProfileName(int id, String name){
        profile profile = profileData.getProfile(id);
        profile.name=name;
        profileData.updateProfile(profile);
    }

    public void updateProfileAge(int id, int age){
        profile profile = profileData.getProfile(id);
        profile.age=age;
        profileData.updateProfile(profile);
    }
    public void updateProfileSex(int id, boolean sex){
        profile profile = profileData.getProfile(id);
        profile.sex=sex;
        profileData.updateProfile(profile);
    }
    public void updateProfileHeight(int id, double height){
        profile profile = profileData.getProfile(id);
        profile.height=height;
        profileData.updateProfile(profile);
    }
    public void updateProfileWeight(int id,double weight){
        profile profile = profileData.getProfile(id);
        profile.weight=weight;
        profileData.updateProfile(profile);
    }

    public void deleteProfile(int id){
        profileData.deleteProfile(id);
    }
}
