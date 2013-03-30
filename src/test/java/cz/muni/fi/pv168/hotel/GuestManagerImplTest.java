/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 *
 * @author wintermute
 */
public class GuestManagerImplTest {
    
    GuestManager guestManager;
    Guest guest,guest2;
    
    public GuestManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        guestManager = new GuestManagerImpl();
        guest = new Guest(0, "root", "01234567", false);
        guest2 = new Guest(1, "bfu", "76543210", true);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createGuest method, of class GuestManagerImpl.
     */
    @Test
    public void testCreateGuest() {
        Guest result = guestManager.createGuest(guest);
        Guest expResult = guest;
        assertEquals(expResult,result);
        fail("Bad guest added");
    }
    
    public void testCreateGuestWithNull() throws Exception {
        try {
            guestManager.createGuest(null);
            fail("Did not threw NullPointerException");
        } catch (NullPointerException ex) {
            
        }
    }

    @Test
    public void testDeleteGuest() {
        guestManager.deleteGuest(guest);
        assertThat(guestManager.listAllGuests(),not(hasItem(guest)));
    }
 
}
