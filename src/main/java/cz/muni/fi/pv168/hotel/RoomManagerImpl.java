/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class RoomManagerImpl implements RoomManager {

    private static final Logger logger = Logger.getLogger(
            GuestManagerImpl.class.getName());
    private JdbcTemplate jdbc;

    public RoomManagerImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    public static final ParameterizedRowMapper<Room> ROOM_MAPPER = new ParameterizedRowMapper<Room>() {
        @Override
        public Room mapRow(ResultSet rs, int i) throws SQLException {
            return new Room(rs.getInt("id"), RoomType.values()[rs.getInt("type")], rs.getShort("beds"), rs.getBoolean("seaview"), rs.getString("note"));
        }
    };

    @Override
    public void createRoom(Room room) {
        try {
            jdbc.update("INSERT INTO room (id,type,beds,seaview,note) VALUES (?,?,?,?,?)", room.getId(), room.getType().ordinal(), room.getBeds(), room.getSeaView(), room.getNote());
            room.setId(jdbc.queryForObject("select last_insert_id()", Integer.class));
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public void updateRoom(Room room) {
        try {
            jdbc.update("UPDATE room SET type=?, beds=?, seaview=?, note=? WHERE id=?", room.getType().ordinal(), room.getBeds(), room.getSeaView(), room.getNote(), room.getId());
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public void deleteRoom(Room room) {
        try {
            jdbc.update("DELETE FROM room WHERE id=?", room.getId());
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public List<Room> listAllRooms() {
        try {
            return jdbc.query("SELECT * FROM room", ROOM_MAPPER);
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting rooms from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }

    @Override
    public Room findRoomById(Integer id) {
        try {
            return jdbc.queryForObject("SELECT * FROM room WHERE id=?", ROOM_MAPPER, id);
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting rooms by id from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }
}
