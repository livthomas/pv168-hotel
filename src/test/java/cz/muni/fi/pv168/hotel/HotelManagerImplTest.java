/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 *
 * @author livthomas
 */
public class HotelManagerImplTest {
    
    static ApplicationContext ctx;
    
    private GuestManager guestManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    
    public HotelManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ctx = new AnnotationConfigApplicationContext(AppTest.SpringConfig.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
        JdbcTemplate jdbc = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
        JdbcTestUtils.executeSqlScript(jdbc, new ClassPathResource("deleteTables.sql"), true);
    }
    
    @Before
    public void setUp() {
        JdbcTemplate jdbc = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
        JdbcTestUtils.executeSqlScript(jdbc, new ClassPathResource("createTables.sql"), true);
        
        guestManager = ctx.getBean("guestManager", GuestManager.class);
        roomManager = ctx.getBean("roomManager", RoomManager.class);
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
        guestManager.createGuest(new Guest(null,"Peter","0000",true));
        Guest guest = guestManager.findGuestById(1);
        Room room = new Room(null, RoomType.APPARTMENT, (short) 2, true, "Bordel");
        roomManager.createRoom(room);
        
        hotelManager.checkIn(guest, room);
        Collection<Guest> result = hotelManager.findGuestsByRoom(room);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of checkOut method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckOut() {
        guestManager.createGuest(new Guest(null,"Peter","0000",true));
        Guest guest = guestManager.findGuestById(1);
        Room room = new Room(null, RoomType.APPARTMENT, (short) 2, true, "Bordel");
        roomManager.createRoom(room);
        hotelManager.checkIn(guest, room);
        
        hotelManager.checkOut(guest);
        Room result = hotelManager.findRoomByGuest(guest);
        assertNull(result);
    }

    /**
     * Test of findGuestsByRoom method, of class HotelManagerImpl.
     */
    @Test
    public void testFindGuestsByRoom() {
        guestManager.createGuest(new Guest(null,"Peter","0000",true));
        Guest guest = guestManager.findGuestById(1);
        Room room = new Room(null, RoomType.APPARTMENT, (short) 2, true, "Bordel");
        roomManager.createRoom(room);        
        hotelManager.checkIn(guest, room);
        
        Collection<Guest> result = hotelManager.findGuestsByRoom(room);
        assertTrue(result.contains(guest));
    }

    /**
     * Test of findRoomByGuest method, of class HotelManagerImpl.
     */
    @Test
    public void testFindRoomByGuest() {
        guestManager.createGuest(new Guest(null,"Peter","0000",true));
        Guest guest = guestManager.findGuestById(1);
        Room room = new Room(null, RoomType.APPARTMENT, (short) 2, true, "Bordel");
        roomManager.createRoom(room);        
        hotelManager.checkIn(guest, room);
        
        Room result = hotelManager.findRoomByGuest(guest);
        assertEquals(room, result);
    }
    
}