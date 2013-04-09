/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import static cz.muni.fi.pv168.hotel.GuestManagerImplTest.ctx;
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
        ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class);
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
        Guest guest = guestManager.createGuest(new Guest(1,"Peter","0000",true));
        Room room = roomManager.createRoom(new Room(1, RoomType.APPARTMENTS, (short) 2, true, "Bordel"));
        
        hotelManager.checkIn(guest, room);
        Collection<Guest> result = hotelManager.findGuestsByRoom(room);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of checkOut method, of class HotelManagerImpl.
     */
    @Test
    public void testCheckOut() {
        Guest guest = guestManager.createGuest(new Guest(1,"Peter","0000",true));
        Room room = roomManager.createRoom(new Room(1, RoomType.APPARTMENTS, (short) 2, true, "Bordel"));
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
        Guest guest = guestManager.createGuest(new Guest(1,"Peter","0000",true));
        Room room = roomManager.createRoom(new Room(1, RoomType.APPARTMENTS, (short) 2, true, "Bordel"));        
        hotelManager.checkIn(guest, room);
        
        Collection<Guest> result = hotelManager.findGuestsByRoom(room);
        assertTrue(result.contains(guest));
    }

    /**
     * Test of findRoomByGuest method, of class HotelManagerImpl.
     */
    @Test
    public void testFindRoomByGuest() {
        Guest guest = guestManager.createGuest(new Guest(1,"Peter","0000",true));
        Room room = roomManager.createRoom(new Room(1, RoomType.APPARTMENTS, (short) 2, true, "Bordel"));        
        hotelManager.checkIn(guest, room);
        
        Room result = hotelManager.findRoomByGuest(guest);
        assertEquals(room, result);
    }
    
}