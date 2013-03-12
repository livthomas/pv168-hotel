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
public class GuestManagerTest extends TestCase {
	
	public GuestManagerTest(String testName) {
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
	 * Test of createGuest method, of class GuestManager.
	 */
	public void testCreateGuest() {
		System.out.println("createGuest");
		Guest guest = null;
		GuestManager instance = new GuestManagerImpl();
		Guest expResult = null;
		Guest result = instance.createGuest(guest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deleteGuest method, of class GuestManager.
	 */
	public void testDeleteGuest() {
		System.out.println("deleteGuest");
		Guest guest = null;
		GuestManager instance = new GuestManagerImpl();
		Guest expResult = null;
		Guest result = instance.deleteGuest(guest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of listAllGuests method, of class GuestManager.
	 */
	public void testListAllGuests() {
		System.out.println("listAllGuests");
		GuestManager instance = new GuestManagerImpl();
		Collection expResult = null;
		Collection result = instance.listAllGuests();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findGuestById method, of class GuestManager.
	 */
	public void testFindGuestById() {
		System.out.println("findGuestById");
		int id = 0;
		GuestManager instance = new GuestManagerImpl();
		Guest expResult = null;
		Guest result = instance.findGuestById(id);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of findGuestByName method, of class GuestManager.
	 */
	public void testFindGuestByName() {
		System.out.println("findGuestByName");
		String name = "";
		GuestManager instance = new GuestManagerImpl();
		Guest expResult = null;
		Guest result = instance.findGuestByName(name);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateGuest method, of class GuestManager.
	 */
	public void testUpdateGuest() {
		System.out.println("updateGuest");
		Guest guest = null;
		GuestManager instance = new GuestManagerImpl();
		Guest expResult = null;
		Guest result = instance.updateGuest(guest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	public class GuestManagerImpl implements GuestManager {

		public Guest createGuest(Guest guest) {
			return null;
		}

		public Guest deleteGuest(Guest guest) {
			return null;
		}

		public Collection<Guest> listAllGuests() {
			return null;
		}

		public Guest findGuestById(int id) {
			return null;
		}

		public Guest findGuestByName(String name) {
			return null;
		}

		public Guest updateGuest(Guest guest) {
			return null;
		}
	}
}
