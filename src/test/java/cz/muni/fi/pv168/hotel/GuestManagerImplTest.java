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
        System.out.println("createGuest");
        Guest result = guestManager.createGuest(guest);
        Guest expResult = guest;
        assertEquals(expResult,result);
        fail("Bad guest added");
    }

    /**
     * Test of deleteGuest method, of class GuestManagerImpl.
     */
    // TODO
    @Test
    public void testDeleteGuest() {
        System.out.println("deleteGuest");
        guest = null;
        GuestManagerImpl instance = new GuestManagerImpl();
        Guest expResult = null;
        Guest result = instance.deleteGuest(guest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAllGuests method, of class GuestManagerImpl.
     */
    // TODO
    @Test
    public void testListAllGuests() {
        System.out.println("listAllGuests");
        GuestManagerImpl instance = new GuestManagerImpl();
        Collection expResult = null;
        Collection result = instance.listAllGuests();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findGuestById method, of class GuestManagerImpl.
     */
    @Test
    public void testFindGuestById() {
        System.out.println("findGuestById");
        guestManager.createGuest(guest);
        guestManager.createGuest(guest2);
        Guest result = guestManager.findGuestById(guest.getId());
        Guest expResult = guest;
        assertEquals(expResult,result);
        fail("Did not found correct guest");
    }

    /**
     * Test of findGuestByName method, of class GuestManagerImpl.
     */
    @Test
    public void testFindGuestByName() {
        System.out.println("findGuestByName");
        Guest guest = new Guest(0, "root", "01234567", false);
        Guest guest2 = new Guest(1, "bfu", "76543210", true);
        guestManager.createGuest(guest);
        guestManager.createGuest(guest2);
        int result = guestManager.findGuestByName(guest.getName()).getId();
        int expResult = guest.getId();
        assertEquals(expResult,result);
        fail("Did not found correct guest");
    }

    /**
     * Test of updateGuest method, of class GuestManagerImpl.
     */
    @Test
    public void testUpdateGuest() {
        System.out.println("updateGuest");
        Guest newGuest = new Guest(1, "admin", "66600666", true);
        Guest result = guestManager.updateGuest(newGuest);
        Guest expResult = newGuest;
        assertEquals(expResult,result);
        fail("Did not update correct guest");
    }
}
