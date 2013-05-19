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
public interface RoomManager {
	
	void createRoom(Room room);
	
	void updateRoom(Room room);
	
	void deleteRoom(Room room);
	
	List<Room> listAllRooms();
	
	Room findRoomById(Integer id);
	
}
