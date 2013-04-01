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
public interface HotelManager {
	
	boolean checkIn(Guest guest, Room room);
	
	boolean checkOut(Guest guest);
	
	Collection<Guest> findGuestsByRoom(Room room);
	
	Room findRoomByGuest(Guest guest);
	
}
