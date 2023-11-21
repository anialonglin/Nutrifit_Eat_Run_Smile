package deliverable1tests;

import application.userManager;
import application.BMICalc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BMICalculation {
    @Test
    public void BMICalc(String username) {
        assertEquals(BMICalc.getBMI(username),((double)180/(double)180/(double)180)*(double)10000,0.1);
    }
}
