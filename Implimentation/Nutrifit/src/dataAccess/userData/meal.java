package dataAccess.userData;

import java.util.Date;

public class meal {
    public int profileId;
    public int mealId;
    public Date date;
    public String name;

    public meal(int profileId, Date date, String name){
        this.profileId=profileId;
        this.date=date;
        this.name=name;
    }
}
