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
public class HotelManagerTest extends TestCase {
	
	public HotelManagerTest(String testName) {
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
	 * Test of checkIn method, of class HotelManager.
	 */
	public void testCheckIn() {
		System.out.println("checkIn");
		Guest guest = null;
		Room room = null;
		HotelManager instance = new HotelManagerImpl();
		boolean expResult = false;
		boolean result = instance.checkIn(guest, room);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of checkOut method, of class HotelManager.
	 */
	public void testCheckOut() {
		System.out.println("checkOut");
		Guest guest = null;
		HotelManager instance = new HotelManagerImpl();
		Guest expResult = null;
		Guest result = instance.checkOut(guest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findGuestsByRoom method, of class HotelManager.
	 */
	public void testFindGuestsByRoom() {
		System.out.println("findGuestsByRoom");
		Room room = null;
		HotelManager instance = new HotelManagerImpl();
		Collection expResult = null;
		Collection result = instance.findGuestsByRoom(room);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findRoomByGuest method, of class HotelManager.
	 */
	public void testFindRoomByGuest() {
		System.out.println("findRoomByGuest");
		Guest guest = null;
		HotelManager instance = new HotelManagerImpl();
		Room expResult = null;
		Room result = instance.findRoomByGuest(guest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	public class HotelManagerImpl implements HotelManager {

		public boolean checkIn(Guest guest, Room room) {
			return false;
		}

		public Guest checkOut(Guest guest) {
			return null;
		}

		public Collection<Guest> findGuestsByRoom(Room room) {
			return null;
		}

		public Room findRoomByGuest(Guest guest) {
			return null;
		}
	}
}
