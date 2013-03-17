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
public interface RoomManager {
	
	Room createRoom(Room room);
	
	Room updateRoom(Room room);
	
	Room deleteRoom(Room room);
	
	Collection<Room> listAllRooms();
	
	Room findRoomById(int id);
	
}
