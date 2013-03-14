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
public class HotelManagerImplTest {
    
    public HotelManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkIn method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckIn() {
        System.out.println("checkIn");
        Guest guest = null;
        Room room = null;
        HotelManagerImpl instance = new HotelManagerImpl();
        boolean expResult = false;
        boolean result = instance.checkIn(guest, room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOut method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckOut() {
        System.out.println("checkOut");
        Guest guest = null;
        HotelManagerImpl instance = new HotelManagerImpl();
        Guest expResult = null;
        Guest result = instance.checkOut(guest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findGuestsByRoom method, of class HotelManagerImpl.
     */
    @Test
    public void testFindGuestsByRoom() {
        System.out.println("findGuestsByRoom");
        Room room = null;
        HotelManagerImpl instance = new HotelManagerImpl();
        Collection expResult = null;
        Collection result = instance.findGuestsByRoom(room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRoomByGuest method, of class HotelManagerImpl.
     */
    @Test
    public void testFindRoomByGuest() {
        System.out.println("findRoomByGuest");
        Guest guest = null;
        HotelManagerImpl instance = new HotelManagerImpl();
        Room expResult = null;
        Room result = instance.findRoomByGuest(guest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
