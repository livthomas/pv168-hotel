/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author livthomas
 */
public class HotelManagerImplTest {
    
    static ApplicationContext ctx;
    
    private HotelManager hotelManager;
    private GuestManager guestManager;
    private Guest guest;
    private Guest guest2;
    private Room room;
    
    public HotelManagerImplTest() {
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
        hotelManager = ctx.getBean("hotelManager", HotelManager.class);
        guest = new Guest(1,"Peter","0000",true);
        room = new Room(1, RoomType.APPARTMENTS, (short) 2, true, "Bordel");
        guestManager = ctx.getBean("guestManager", GuestManager.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkIn method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckIn() {
        // overit ci sa po zavolani checkIn ulozi do databazy hodnota room_id
        hotelManager.checkIn(guest, room);
        Collection<Guest> result = hotelManager.findGuestsByRoom(room);
        assertTrue(!result.isEmpty());   
    }

    /**
     * Test of checkOut method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckOut() {
        // overit ci sa po zavolani checkOut ulozi do databazy hodnota room_id = NULL
        hotelManager.checkOut(guest);
        Room result = hotelManager.findRoomByGuest(guest);
        assertTrue(result == null);
    }

    /**
     * Test of findGuestsByRoom method, of class HotelManagerImpl.
     */
    @Test
    public void testFindGuestsByRoom() {
        // pridat dvoch a zistit ci sa vrati kolekcia ktora ich obsahuje
        hotelManager.checkIn(guest, room);
        hotelManager.checkIn(guest2, room);
        Collection<Guest> result = new ArrayList<Guest>();
        result.add(guest);
        result.add(guest2);
        assertTrue(result.equals(hotelManager.findGuestsByRoom(room)));
    }

    /**
     * Test of findRoomByGuest method, of class HotelManagerImpl.
     */
    @Test
    public void testFindRoomByGuest() {
        hotelManager.checkIn(guest, room);
        Room result = hotelManager.findRoomByGuest(guest);
        assertTrue(result.equals(room));
    }
}