package deliverable1tests;

import application.userManager;
import org.junit.Test;
import static org.junit.Assert.*;

//these junit tests act in place of the user interface interactions for the purposes of deliverable 1, data that would originate from the UI is given directly.

public class profileCreation {

    @Test
    public void createProfile(){ //creating a profile from scratch.
        int id = userManager.createUserProfile("John", 35, true, 180, 180);
        assertEquals(userManager.getUserProfile(id).name,"John");
        assertEquals(userManager.getUserProfile(id).age,35);
        assertTrue(userManager.getUserProfile(id).sex);
        assertEquals(userManager.getUserProfile(id).height,180, 0.1);
        assertEquals(userManager.getUserProfile(id).weight,180, 0.1);
    }
    @Test(expected = IndexOutOfBoundsException.class) //Test will fail if there is not an IndexOutOfBoundsException
    public void deleteProfile(){ //showing that a deleted profile is actually deleted
        int id = userManager.createUserProfile("John", 35, true, 180, 180);
        userManager.deleteProfile(id);
        assertNotEquals(userManager.getUserProfile(id).name,"John");
        assertNotEquals(userManager.getUserProfile(id).age,35);
        assertFalse(userManager.getUserProfile(id).sex);
        assertNotEquals(userManager.getUserProfile(id).height,180, 0.1);
        assertNotEquals(userManager.getUserProfile(id).weight,180, 0.1);
    }

    @Test
    public void editProfile(){ //creating a profile from scratch.
        int id = userManager.createUserProfile("John", 35, true, 180, 180);
        assertEquals(userManager.getUserProfile(id).name,"John");
        assertEquals(userManager.getUserProfile(id).age,35);
        assertTrue(userManager.getUserProfile(id).sex);
        assertEquals(userManager.getUserProfile(id).height,180, 0.1);
        assertEquals(userManager.getUserProfile(id).weight,180, 0.1);
        userManager.updateProfileName(id, "bob");
        userManager.updateProfileAge(id,100);
        userManager.updateProfileSex(id,false);
        userManager.updateProfileHeight(id,30);
        userManager.updateProfileWeight(id,300);
        assertNotEquals(userManager.getUserProfile(id).name,"John");
        assertNotEquals(userManager.getUserProfile(id).age,35);
        assertNotEquals(userManager.getUserProfile(id).sex, true);
        assertNotEquals(userManager.getUserProfile(id).height,180, 0.1);
        assertNotEquals(userManager.getUserProfile(id).weight,180, 0.1);
        assertEquals(userManager.getUserProfile(id).name,"bob");
        assertEquals(userManager.getUserProfile(id).age,100);
        assertEquals(userManager.getUserProfile(id).sex,false);
        assertEquals(userManager.getUserProfile(id).height,30, 0.1);
        assertEquals(userManager.getUserProfile(id).weight,300, 0.1);
    }
}
