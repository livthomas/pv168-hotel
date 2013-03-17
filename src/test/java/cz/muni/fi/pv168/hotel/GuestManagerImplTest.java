/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author livthomas
 */
public class GuestManagerImplTest extends TestCase {
    
    public GuestManagerImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createGuest method, of class GuestManagerImpl.
     */
    public void testCreateGuest() {
        System.out.println("createGuest");
        GuestManager manager = new GuestManagerImpl();
        Guest guest = newGuest("Johny Bravo", "1111222233334444", true);
        
        // ID assignation
        int guestId = guest.getId();
        assertNotNull(guestId);
        
        // guest insertion
        Guest result = manager.createGuest(guest);
        assertEquals(guest, result);
    }

    /**
     * Test of updateGuest method, of class GuestManagerImpl.
     */
    public void testUpdateGuest() {
        System.out.println("updateGuest");
        GuestManager manager = new GuestManagerImpl();
        
        Guest guest = manager.createGuest(newGuest("Johny Bravo", "1111222233334444", true));
        guest.setName("Sherlock Holmes");
        
        manager.updateGuest(guest);
        
        Guest result = manager.findGuestById(guest.getId());
        assertEquals(guest, result);
    }

    /**
     * Test of deleteGuest method, of class GuestManagerImpl.
     */
    public void testDeleteGuest() {
        System.out.println("deleteGuest");
        GuestManager manager = new GuestManagerImpl();
        Guest guest1 = newGuest("Johny Bravo", "1111222233334444", true);
        Guest guest2 = newGuest("Pamela Anderson", "9999999999999999", false);
        
        manager.createGuest(guest1);
        manager.createGuest(guest2);
        
        manager.deleteGuest(guest1);
        
        assertNull(manager.findGuestById(guest1.getId()));
        assertNotNull(manager.findGuestById(guest2.getId()));
    }
    
    private Guest newGuest(String name, String creditCard, boolean vip) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setCreditCard(creditCard);
        guest.setVip(vip);
        return guest;
    }
    
}
