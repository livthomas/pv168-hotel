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
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.hasItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 *
 * @author livthomas
 */
public class RoomManagerImplTest {
    
    static ApplicationContext ctx;
    
    private RoomManager roomManager;
    
    public RoomManagerImplTest() {
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
        
        roomManager = ctx.getBean("roomManager", RoomManager.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createRoom method, of class RoomManagerImpl.
     */
    @Test
    public void testCreateRoom() {
        Room room = new Room(0, RoomType.APPARTMENTS, (short) 2, true, "neupratana");
        Room result = roomManager.createRoom(room);
        Room expResult = room;
        assertEquals(expResult,result);
    }
    
    public void testCreateRoomWithNull() throws Exception {
        try {
            roomManager.createRoom(null);
            fail("Did not threw NullPointerException");
        } catch (NullPointerException ex) {}
    }
    
    @Test
    public void testUpdateRoom() {
        Room room = new Room(0, RoomType.APPARTMENTS, (short) 2, true, "neupratana");
        roomManager.createRoom(room);
        room.setNote("upratana");
        Room result = roomManager.updateRoom(room);
        assertEquals(room, result);
    }

    @Test
    public void testDeleteRoom() {
        Room room = new Room(0, RoomType.APPARTMENTS, (short) 2, true, "neupratana");
        roomManager.createRoom(room);
        roomManager.deleteRoom(room);
        Room result = roomManager.findRoomById(room.getId());
        assertNull(result);
    }
    
    @Test
    public void testListAllRooms() {
        Collection<Room> roomsBefore = roomManager.listAllRooms();
        Room room = new Room(0, RoomType.APPARTMENTS, (short) 2, true, "neupratana");
        roomManager.createRoom(room);
        Collection<Room> roomsAfter = roomManager.listAllRooms();
        assertEquals(roomsAfter.size(), roomsBefore.size() + 1);
    }
    
    @Test
    public void testFindRoomById() {
        Room room = roomManager.createRoom(new Room(1, RoomType.APPARTMENTS, (short) 2, true, "neupratana"));
        Room result = roomManager.findRoomById(room.getId());
        assertEquals(room, result);
    }
    
}
