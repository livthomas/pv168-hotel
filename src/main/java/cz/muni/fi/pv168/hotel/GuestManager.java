/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.List;

/**
 *
 * @author livthomas
 */
public interface GuestManager {
	
	void createGuest(Guest guest);
	
	void updateGuest(Guest guest);
	
	void deleteGuest(Integer id);
	
	List<Guest> listAllGuests();
	
	Guest findGuestById(int id);
	
	List<Guest> findGuestsByName(String name);
	
}
