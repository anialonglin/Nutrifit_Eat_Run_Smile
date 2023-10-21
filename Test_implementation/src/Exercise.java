public class Exercise {
    private String name;
    private int duration;
    private int burnedCal;
    private String type;
    private String intensity;

    public Exercise(String name, int duration, String type, String intensity) {
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.intensity = intensity;
        this.burnedCal = caloryCount();
    }

    public int getDuration() {
        return duration;
    }

    public int getBurnedCal() {
        return burnedCal;
    }

    public String getType() {
        return type;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getName() {
        return name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setBurnedCal(int burnedCal) {
        this.burnedCal = burnedCal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int caloryCount() {
        double baseCaloriesPerMinute = 4;

        switch (type) {
            case "Running":
                baseCaloriesPerMinute *= 6;
                break;
            case "Biking":
                baseCaloriesPerMinute *= 5;
                break;
            case "Swimming":
                baseCaloriesPerMinute *= 7;
                break;
            // Add more cases for other exercise types
        }

        switch (intensity) {
            case "High":
                baseCaloriesPerMinute *= 1.4;
                break;
            case "Very High":
                baseCaloriesPerMinute *= 1.6;
                break;
            case "Medium":
                baseCaloriesPerMinute *= 1.2;
                break;
            // Add more cases for other intensity levels
        }

        return (int) (baseCaloriesPerMinute * duration);
    }
}
