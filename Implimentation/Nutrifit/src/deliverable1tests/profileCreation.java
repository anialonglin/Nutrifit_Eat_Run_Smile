package deliverable1tests;

import application.userManager;
import org.junit.Test;
import static org.junit.Assert.*;

//these junit tests act in place of the user interface interactions for the purposes of deliverable 1, data that would originate from the UI is given directly.

public class profileCreation {

    @Test
    public void createProfile(){ //creating a profile from scratch.
        userManager manager = new userManager();
        int id = manager.createUserProfile("John", 35, true, 180, 180);
        assertEquals(manager.getUserProfile(id).name,"John");
        assertEquals(manager.getUserProfile(id).age,35);
        assertTrue(manager.getUserProfile(id).sex);
        assertEquals(manager.getUserProfile(id).height,180, 0.1);
        assertEquals(manager.getUserProfile(id).weight,180, 0.1);
    }
    @Test(expected = IndexOutOfBoundsException.class) //Test will fail if there is not an IndexOutOfBoundsException
    public void deleteProfile(){ //showing that a deleted profile is actually deleted
        userManager manager = new userManager();
        int id = manager.createUserProfile("John", 35, true, 180, 180);
        manager.deleteProfile(id);
        assertNotEquals(manager.getUserProfile(id).name,"John");
        assertNotEquals(manager.getUserProfile(id).age,35);
        assertFalse(manager.getUserProfile(id).sex);
        assertNotEquals(manager.getUserProfile(id).height,180, 0.1);
        assertNotEquals(manager.getUserProfile(id).weight,180, 0.1);
    }

    @Test
    public void editProfile(){ //creating a profile from scratch.
        userManager manager = new userManager();
        int id = manager.createUserProfile("John", 35, true, 180, 180);
        assertEquals(manager.getUserProfile(id).name,"John");
        assertEquals(manager.getUserProfile(id).age,35);
        assertTrue(manager.getUserProfile(id).sex);
        assertEquals(manager.getUserProfile(id).height,180, 0.1);
        assertEquals(manager.getUserProfile(id).weight,180, 0.1);
        manager.updateProfileName(id, "bob");
        manager.updateProfileAge(id,100);
        manager.updateProfileSex(id,false);
        manager.updateProfileHeight(id,30);
        manager.updateProfileWeight(id,300);
        assertNotEquals(manager.getUserProfile(id).name,"John");
        assertNotEquals(manager.getUserProfile(id).age,35);
        assertNotEquals(manager.getUserProfile(id).sex, true);
        assertNotEquals(manager.getUserProfile(id).height,180, 0.1);
        assertNotEquals(manager.getUserProfile(id).weight,180, 0.1);
        assertEquals(manager.getUserProfile(id).name,"bob");
        assertEquals(manager.getUserProfile(id).age,100);
        assertEquals(manager.getUserProfile(id).sex,false);
        assertEquals(manager.getUserProfile(id).height,30, 0.1);
        assertEquals(manager.getUserProfile(id).weight,300, 0.1);
    }
}
