/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class GuestManagerImpl implements GuestManager {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert insertGuest;
    private boolean supportsGetGeneratedKeys;
    private String databaseProductName;
    private DataSource ds;

    /*
     * TODO - toto tu treba vyriesit, ten dataSource vytiahnut z tomcat (?). Som z toho kon :D
     */
    
    
//    @Resource
//    public void setDataSource(DataSource dataSource) {
//        this.jdbc = new JdbcTemplate(dataSource);
//        this.insertGuest = new SimpleJdbcInsert(dataSource)
//                .withTableName("GUEST")
//                .usingColumns("name", "credit_card", "vip")
//                .usingGeneratedKeyColumns("id");
//
//        try {
//            Connection c = dataSource.getConnection();
//            this.supportsGetGeneratedKeys = c.getMetaData().supportsGetGeneratedKeys();
//            this.databaseProductName = c.getMetaData().getDatabaseProductName();
//            c.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    private static final ParameterizedRowMapper<Guest> GUEST_MAPPER = new ParameterizedRowMapper<Guest>() {
        public Guest mapRow(ResultSet rs, int i) throws SQLException {
            return new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("credit_card"), rs.getBoolean("vip"));
        }
    };
    
    public Guest createGuest(Guest guest) {
        jdbc.update("INSERT INTO GUEST(id, name, credit_card, vip) "
        + "VALUES (?,?,?,?)",guest.getId(), guest.getName(), guest.getCreditCard(), guest.isVip());
        return guest;
    }

    public Guest updateGuest(Guest guest) {
        jdbc.update("UPDATE GUEST SET name=? credit_card=? vip=? WHERE id=?",
        guest.getName(), guest.getCreditCard(), guest.isVip());
        return guest;
    }

    public Guest deleteGuest(Guest guest) {
        jdbc.update("DELETE FROM GUEST WHERE id=?", guest.getId());
        return guest;
    }

    public Collection<Guest> listAllGuests() {
        return jdbc.query("SELECT * FROM GUEST",GUEST_MAPPER);
    }

    public Guest findGuestById(int id) {
        return jdbc.queryForObject("SELECT * FROM GUEST WHERE id=?",GUEST_MAPPER,id);
    }

    public Collection<Guest> findGuestsByName(String name) {
        return jdbc.query("SELECT * FROM GUEST WHERE name like ?", GUEST_MAPPER, name);
    }
    
}
