/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


public class RoomManagerImpl implements RoomManager {
    
    private JdbcTemplate jdbc;
    private SimpleJdbcInsert insertRoom;
    private boolean supportsGetGeneratedKeys;
    private String databaseProductName;
    private DataSource ds;
    
    private static final ParameterizedRowMapper<Room> ROOM_MAPPER = new ParameterizedRowMapper<Room>() {
        public Room mapRow(ResultSet rs, int i) throws SQLException {
            return new Room(rs.getInt("id"), RoomType.valueOf(rs.getString("type")), rs.getShort("beds"), rs.getBoolean("seaview"), rs.getString("note"));
        }
    };
    
    public Room createRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("INSERT INTO GUEST VALUES (id,type,beds,seaview,note) VALUES ? ? ? ? ?", room.getId(), room.getType().toString(), room.getBeds(), room.isSeaView(), room.getNote());
        return room;
    }

    public Room updateRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("UPDATE ROOM SET type=? beds=? seaview=? note=? WHERE id=?", room.getType().toString(), room.getBeds(), room.isSeaView(), room.getNote(), room.getId());
        return room;
    }

    public Room deleteRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("DELETE FROM GUEST WHERE id=?", room.getId());
        return room;
    }

    public Collection<Room> listAllRooms() throws UnsupportedOperationException {
        return jdbc.query("SELECT * FROM ROOM", ROOM_MAPPER);
    }

    public Room findRoomById(int id) throws UnsupportedOperationException {
        return jdbc.queryForObject("SELECT * FROM ROOM WHERE id=?",ROOM_MAPPER, id);
    }
    
}
