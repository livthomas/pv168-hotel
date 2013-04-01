/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

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

/**
 *
 * @author livthomas
 */
public class RoomManagerImplTest {
    
    static ApplicationContext ctx;
    
    private RoomManager roomManager;
    private Room room, room2;
    
    public RoomManagerImplTest() {
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
        roomManager = ctx.getBean("roomManager", RoomManager.class);
        
        room = new Room(0, RoomType.APPARTMENTS, (short) 2, true, "neupratana");
        room2 = new Room(1, RoomType.APPARTMENTS, (short) 1, false, "rozbita");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createRoom method, of class RoomManagerImpl.
     */
    @Test
    public void testCreateRoom() {
        Room result = roomManager.createRoom(room);
        Room expResult = room;
        assertEquals(expResult,result);
    }
    
    public void testCreateRoomWithNull() throws Exception {
        try {
            roomManager.createRoom(null);
            fail("Did not threw NullPointerException");
        } catch (NullPointerException ex) {
            
        }
    }

    @Test
    public void testDeleteRoom() {
        roomManager.deleteRoom(room);
        assertThat(roomManager.listAllRooms(),not(hasItem(room)));
    }
}
