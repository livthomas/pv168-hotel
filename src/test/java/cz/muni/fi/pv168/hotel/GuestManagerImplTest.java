/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.hasItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author wintermute
 */
public class GuestManagerImplTest {
    
    static ApplicationContext ctx;
    
    GuestManager guestManager;
    Guest guest,guest2;
    
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
