package dataAccess.userData;

import java.util.ArrayList;

public class profile {
    public int id;
    public String name;
    public int age;
    public boolean sex;
    public double height;
    public double weight;

    ArrayList<exercise> exerciseLog = new ArrayList<>();

    public profile(String name, int age, boolean sex, double height, double weight) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }
}