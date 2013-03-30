/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 *
 * @author livthomas
 */
public class RoomManagerImplTest {
    RoomManager roomManager;
    Room room,room2;
    
    public RoomManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        roomManager = new RoomManagerImpl();
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
        fail("Bad room added");
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