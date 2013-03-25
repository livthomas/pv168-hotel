/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;

/**
 *
 * @author livthomas
 */
public class RoomManagerImplTest extends TestCase {
	
	public RoomManagerImplTest(String testName) {
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
	 * Test of createRoom method, of class RoomManagerImpl.
	 */
	public void testCreateRoom() {
		System.out.println("createRoom");
		RoomManager manager = new RoomManagerImpl();
		Room room = newRoom(RoomType.APPARTMENTS, (short)4, true, null);
		
		// ID assignation
		Room result = manager.createRoom(room);
		assertNotNull(result.getId());
		
		// room insertion
		room.setId(result.getId());
		assertEquals(room, result);
	}

	/**
	 * Test of updateRoom method, of class RoomManagerImpl.
	 */
	public void testUpdateRoom() {
		System.out.println("updateRoom");
		RoomManager manager = new RoomManagerImpl();
		
		Room room = manager.createRoom(newRoom(RoomType.APPARTMENTS, (short)4, true, null));
		room.setNote("Very dirty");
		
		Room result = manager.updateRoom(room);
		assertEquals(room, result);
	}

	/**
	 * Test of deleteRoom method, of class RoomManagerImpl.
	 */
	public void testDeleteRoom() {
		System.out.println("deleteRoom");
		RoomManager manager = new RoomManagerImpl();
		Room room1 = newRoom(RoomType.APPARTMENTS, (short)4, true, null);
		Room room2 = newRoom(RoomType.APPARTMENTS, (short)6, false, null);
		
		manager.createRoom(room1);
		manager.createRoom(room2);
		
		manager.deleteRoom(room1);
		
		assertNull(manager.findRoomById(room1.getId()));
		assertNotNull(manager.findRoomById(room2.getId()));
	}
	
	private Room newRoom(RoomType type, short beds, boolean seaView, String note) {
		Room room = new Room();
		room.setType(type);
		room.setBeds(beds);
		room.setSeaView(seaView);
		room.setNote(note);
		return room;		
	}
	
}
