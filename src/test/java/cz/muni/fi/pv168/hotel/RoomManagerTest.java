/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import junit.framework.TestCase;

/**
 *
 * @author livthomas
 */
public class RoomManagerTest extends TestCase {
	
	public RoomManagerTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test of createRoom method, of class RoomManager.
	 */
	public void testCreateRoom() {
		System.out.println("createRoom");
		Room room = null;
		RoomManager instance = new RoomManagerImpl();
		Room expResult = null;
		Room result = instance.createRoom(room);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deleteRoom method, of class RoomManager.
	 */
	public void testDeleteRoom() {
		System.out.println("deleteRoom");
		Room room = null;
		RoomManager instance = new RoomManagerImpl();
		Room expResult = null;
		Room result = instance.deleteRoom(room);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of listAllRooms method, of class RoomManager.
	 */
	public void testListAllRooms() {
		System.out.println("listAllRooms");
		RoomManager instance = new RoomManagerImpl();
		Collection expResult = null;
		Collection result = instance.listAllRooms();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findRoomById method, of class RoomManager.
	 */
	public void testFindRoomById() {
		System.out.println("findRoomById");
		int id = 0;
		RoomManager instance = new RoomManagerImpl();
		Room expResult = null;
		Room result = instance.findRoomById(id);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateRoom method, of class RoomManager.
	 */
	public void testUpdateRoom() {
		System.out.println("updateRoom");
		Room room = null;
		RoomManager instance = new RoomManagerImpl();
		Room expResult = null;
		Room result = instance.updateRoom(room);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	public class RoomManagerImpl implements RoomManager {

		public Room createRoom(Room room) {
			return null;
		}

		public Room deleteRoom(Room room) {
			return null;
		}

		public Collection<Room> listAllRooms() {
			return null;
		}

		public Room findRoomById(int id) {
			return null;
		}

		public Room updateRoom(Room room) {
			return null;
		}
	}
}
