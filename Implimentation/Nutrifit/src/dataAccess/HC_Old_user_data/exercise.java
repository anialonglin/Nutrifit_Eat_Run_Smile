package dataAccess.HC_Old_user_data;

public class exercise {
    public int profileId;
    public int exerciseId;
    public String name;
    public int duration;
    public int intensity;

    public exercise(int profileId, String name, int duration, int intensity) {
        this.profileId = profileId;
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
    }
}
