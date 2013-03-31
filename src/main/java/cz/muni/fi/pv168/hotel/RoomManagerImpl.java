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


public class RoomManagerImpl implements RoomManager {
    
    private JdbcTemplate jdbc;
    
    public RoomManagerImpl() {}
    
    public RoomManagerImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    
    private static final ParameterizedRowMapper<Room> ROOM_MAPPER = new ParameterizedRowMapper<Room>() {
        @Override
        public Room mapRow(ResultSet rs, int i) throws SQLException {
            return new Room(rs.getInt("id"), RoomType.values()[rs.getInt("type")], rs.getShort("beds"), rs.getBoolean("seaview"), rs.getString("note"));
        }
    };
    
    @Override
    public Room createRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("INSERT INTO room (id,type,beds,seaview,note) VALUES (?,?,?,?,?)", room.getId(), room.getType().ordinal(), room.getBeds(), room.isSeaView(), room.getNote());
        return room;
    }

    @Override
    public Room updateRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("UPDATE room SET type=?, beds=?, seaview=?, note=? WHERE id=?", room.getType().ordinal(), room.getBeds(), room.isSeaView(), room.getNote(), room.getId());
        return room;
    }

    @Override
    public Room deleteRoom(Room room) throws UnsupportedOperationException {
        jdbc.update("DELETE FROM room WHERE id=?", room.getId());
        return room;
    }

    @Override
    public Collection<Room> listAllRooms() throws UnsupportedOperationException {
        return jdbc.query("SELECT * FROM room", ROOM_MAPPER);
    }

    @Override
    public Room findRoomById(int id) throws UnsupportedOperationException {
        return jdbc.queryForObject("SELECT * FROM room WHERE id=?",ROOM_MAPPER, id);
    }
    
}
