/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

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
    }

    /**
     * Test of checkOut method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckOut() {
        // overit ci sa po zavolani checkOut ulozi do databazy hodnota room_id = NULL
    }

    /**
     * Test of findGuestsByRoom method, of class HotelManagerImpl.
     */
    @Test
    public void testFindGuestsByRoom() {
        // pridat dvoch a zistit ci sa vrati kolekcia ktora ich obsahuje
    }

    /**
     * Test of findRoomByGuest method, of class HotelManagerImpl.
     */
    @Test
    public void testFindRoomByGuest() {
        // daco
    }
}