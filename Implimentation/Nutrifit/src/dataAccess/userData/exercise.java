package dataAccess.userData;

public class exercise {
    int profileId;
    int exerciseId;
    String name;
    int duration;
    int intensity;

    exercise(int profileId, String name, int duration, int intensity) {
        this.profileId = profileId;
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
    }
}
