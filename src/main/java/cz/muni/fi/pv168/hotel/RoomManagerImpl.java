/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class RoomManagerImpl implements RoomManager {

    List<Room> listOfRooms = new ArrayList<Room>();
    
    public Room createRoom(Room room) {
        listOfRooms.add(room);
        return room;
    }

    public Room deleteRoom(Room room) {
        listOfRooms.remove(room);
        return room;
    }

    public Collection<Room> listAllRooms() {
        return Collections.unmodifiableList(listOfRooms);
    }

    public Room findRoomById(int id) {
        Room tempRoom;
        for(int i = 0; i < listOfRooms.size(); i++) {
            tempRoom = (Room)listOfRooms.get(i);
            if(tempRoom.getId()==id) {
                return tempRoom;
            }
        }
        return null;
    }

    public Room updateRoom(Room room) {
        Room tempRoom = findRoomById(room.getId());
        if(tempRoom == null) {
            return null;
        }
        deleteRoom(tempRoom);
        createRoom(room);
        return room;
    }   
}
