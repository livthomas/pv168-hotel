/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;

/**
 *
 * @author livthomas
 */
public interface GuestManager {
	
	Guest createGuest(Guest guest);
	
	Guest deleteGuest(Guest guest);
	
	Collection<Guest> listAllGuests();
	
	Guest findGuestById(int id);
	
	Guest findGuestByName(String name);
	
	Guest updateGuest(Guest guest);
	
}
