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
	
	void createGuest(Guest guest);
	
	void updateGuest(Guest guest);
	
	void deleteGuest(Integer id);
	
	Collection<Guest> listAllGuests();
	
	Guest findGuestById(int id);
	
	Collection<Guest> findGuestsByName(String name);
	
}
