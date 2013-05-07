/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author livthomas
 */
public class HotelManagerImpl implements HotelManager {

    private static final Logger logger = Logger.getLogger(
            GuestManagerImpl.class.getName());
    private JdbcTemplate jdbc;

    public HotelManagerImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean checkIn(Guest guest, Room room) {
        try {
            int result = jdbc.update("UPDATE guest SET room_id=? WHERE id=?", room.getId(), guest.getId());
            return result == 1;
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
            return false;
        }
    }

    @Override
    public boolean checkOut(Guest guest) {
        try {
            int result = jdbc.update("UPDATE guest SET room_id=NULL WHERE id=?", guest.getId());
            return result == 1;
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
            return false;
        }
    }

    @Override
    public Collection<Guest> findGuestsByRoom(Room room) {
        try {
            return jdbc.query("SELECT * FROM guest WHERE room_id=?", GuestManagerImpl.GUEST_MAPPER, room.getId());
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting guest by room from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }

    @Override
    public Room findRoomByGuest(Guest guest) {
        try {
            return jdbc.queryForObject("SELECT * FROM room WHERE id = (SELECT room_id FROM guest WHERE id=?)", RoomManagerImpl.ROOM_MAPPER, guest.getId());
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting room by guest from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }
}
