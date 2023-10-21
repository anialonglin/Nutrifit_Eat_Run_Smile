package deliverable1tests;

import application.userManager;
import application.BMICalc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BMICalculation {
    @Test
    public void BMICalc() {
        int profileId = userManager.createUserProfile("John", 35, true, 180, 180);
        assertEquals(BMICalc.getBMI(profileId),((double)180/(double)180/(double)180)*(double)10000,0.1);
    }
}
