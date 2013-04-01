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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author wintermute
 */
public class GuestManagerImplTest {
    
    static ApplicationContext ctx;
    
    private GuestManager guestManager;
    
    public GuestManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        guestManager = ctx.getBean("guestManager", GuestManager.class);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateGuest() {
        Guest guest = new Guest(0, "root", "01234567", false);
        Guest result = guestManager.createGuest(guest);
        assertEquals(guest, result);
        guestManager.deleteGuest(guest);
    }
    
    @Test
    public void testCreateGuestWithNull() throws Exception {
        try {
            guestManager.createGuest(null);
            fail("Did not threw NullPointerException");
        } catch (NullPointerException ex) {}
    }
    
    @Test
    public void testUpdateGuest() {
        Guest guest = new Guest(0, "root", "01234567", false);
        guestManager.createGuest(guest);
        guest.setName("toor");
        Guest result = guestManager.updateGuest(guest);
        assertEquals(guest, result);
        guestManager.deleteGuest(guest);
    }

    @Test
    public void testDeleteGuest() {
        Guest guest = new Guest(0, "root", "01234567", false);
        guestManager.createGuest(guest);
        guestManager.deleteGuest(guest);
        Guest result = guestManager.findGuestById(guest.getId());
        assertNull(result);
    }
    
    @Test
    public void testListAllGuests() {
        Collection<Guest> guestsBefore = guestManager.listAllGuests();
        Guest guest = new Guest(0, "root", "01234567", false);
        guestManager.createGuest(guest);
        Collection<Guest> guestsAfter = guestManager.listAllGuests();
        assertEquals(guestsAfter.size(), guestsBefore.size() + 1);
        guestManager.deleteGuest(guest);
    }
    
    @Test
    public void testFindGuestById() {
        Guest guest = guestManager.createGuest(new Guest(0, "root", "01234567", false));
        Guest result = guestManager.findGuestById(guest.getId());
        assertEquals(guest, result);
        guestManager.deleteGuest(guest);
    }
    
    @Test
    public void testFindGuestsByName() {
        Guest guest = guestManager.createGuest(new Guest(0, "root", "01234567", false));
        Collection<Guest> result = guestManager.findGuestsByName("root");
        assertTrue(result.contains(guest));
        guestManager.deleteGuest(guest);
    }
 
}
