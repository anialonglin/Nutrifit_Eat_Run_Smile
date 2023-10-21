public class UserProfile {
    private int id;
    private String name;
    private int age;
    private char sex;
    private double height;
    private double weight;
    private double bmr; // Added BMR field

    public UserProfile(int id, String name, int age, char sex, double height, double weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.bmr = calculateBMR(); // Calculate and store BMR
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBMR() {
        return bmr;
    }

    public Object[] toArray() {
        return new Object[]{id, name, age, sex, height, weight, bmr};
    }

    private double calculateBMR() {
        double bmr;
        if (sex == 'M') {
            // BMR calculation for males
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (sex == 'F') {
            // BMR calculation for females
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        } else {
            bmr = 0; // Invalid sex
        }
        return bmr;
    }
}
