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
public class RoomManagerImplTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createRoom method, of class RoomManagerImpl.
     */
    @Test
    public void testCreateRoom() {
        System.out.println("createRoom");
        Room room = null;
        RoomManagerImpl instance = new RoomManagerImpl();
        Room expResult = null;
        Room result = instance.createRoom(room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRoom method, of class RoomManagerImpl.
     */
    @Test
    public void testDeleteRoom() {
        System.out.println("deleteRoom");
        Room room = null;
        RoomManagerImpl instance = new RoomManagerImpl();
        Room expResult = null;
        Room result = instance.deleteRoom(room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAllRooms method, of class RoomManagerImpl.
     */
    @Test
    public void testListAllRooms() {
        System.out.println("listAllRooms");
        RoomManagerImpl instance = new RoomManagerImpl();
        Collection expResult = null;
        Collection result = instance.listAllRooms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRoomById method, of class RoomManagerImpl.
     */
    @Test
    public void testFindRoomById() {
        System.out.println("findRoomById");
        int id = 0;
        RoomManagerImpl instance = new RoomManagerImpl();
        Room expResult = null;
        Room result = instance.findRoomById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRoom method, of class RoomManagerImpl.
     */
    @Test
    public void testUpdateRoom() {
        System.out.println("updateRoom");
        Room room = null;
        RoomManagerImpl instance = new RoomManagerImpl();
        Room expResult = null;
        Room result = instance.updateRoom(room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
