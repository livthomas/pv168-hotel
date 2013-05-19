/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.DataAccessException;

public class GuestManagerImpl implements GuestManager {

    private static final Logger logger = Logger.getLogger(
            GuestManagerImpl.class.getName());
    
    private JdbcTemplate jdbc;

    public GuestManagerImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    public static final ParameterizedRowMapper<Guest> GUEST_MAPPER = new ParameterizedRowMapper<Guest>() {
        @Override
        public Guest mapRow(ResultSet rs, int i) throws SQLException {
            return new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("credit_card"), rs.getBoolean("vip"));
        }
    };

    @Override
    public void createGuest(Guest guest) {
        try {
            jdbc.update("INSERT INTO guest(id, name, credit_card, vip) VALUES (?,?,?,?)", guest.getId(), guest.getName(), guest.getCreditCard(), guest.getVip());
            guest.setId(jdbc.queryForObject("select last_insert_id()", Integer.class));
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public void updateGuest(Guest guest) {
        try {
            jdbc.update("UPDATE guest SET name=?, credit_card=?, vip=? WHERE id=?", guest.getName(), guest.getCreditCard(), guest.getVip(), guest.getId());
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public void deleteGuest(Integer id) {
        try {
            jdbc.update("DELETE FROM guest WHERE id=?", id);
        } catch (DataAccessException ex) {
            String msg = "Error accessing the data.";
            logger.log(Level.SEVERE, msg, ex);
        }
    }

    @Override
    public List<Guest> listAllGuests() {
        try {
            return jdbc.query("SELECT * FROM guest", GUEST_MAPPER);
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting guests by name from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }

    @Override
    public Guest findGuestById(int id) {
        try {
            return jdbc.queryForObject("SELECT * FROM guest WHERE id=?", GUEST_MAPPER, id);
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting guest from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }

    @Override
    public List<Guest> findGuestsByName(String name) {
        try {
            return jdbc.query("SELECT * FROM guest WHERE name LIKE ?", GUEST_MAPPER, name);
        } catch (EmptyResultDataAccessException ex) {
            String msg = "Error when getting guests by name from DB";
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }
}
