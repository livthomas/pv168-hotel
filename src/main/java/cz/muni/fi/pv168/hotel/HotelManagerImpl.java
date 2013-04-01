/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author livthomas
 */
public class HotelManagerImpl implements HotelManager {
    
    private JdbcTemplate jdbc;
    
    public HotelManagerImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
	
    @Override
	public boolean checkIn(Guest guest, Room room) {
        int result = jdbc.update("UPDATE guest SET room_id=? WHERE id=?", room.getId(), guest.getId());
        return result == 1;
    }
	
    @Override
	public boolean checkOut(Guest guest) {
        int result = jdbc.update("UPDATE guest SET room_id=NULL WHERE id=?", guest.getId());
        return result == 1;
    }
	
    @Override
	public Collection<Guest> findGuestsByRoom(Room room) {
        return jdbc.query("SELECT * FROM guest WHERE room_id=?", GuestManagerImpl.GUEST_MAPPER, room.getId());
    }
	
    @Override
	public Room findRoomByGuest(Guest guest) {
        return (Room) jdbc.query("SELECT * FROM room WHERE id = (SELECT room_id FROM guest WHERE id=?)", RoomManagerImpl.ROOM_MAPPER, guest.getId());
    }
    
}
