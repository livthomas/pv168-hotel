/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GuestManagerImpl implements GuestManager {
    
    private Connection conn;
    public static final Logger logger = Logger.getLogger(GuestManagerImpl.class.getName());
    
    public GuestManagerImpl(){}
    
    public GuestManagerImpl(Connection conn) {
        this.conn = conn;
    }
    
    public Guest createGuest(Guest guest) throws ServiceFailureException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO GUEST (id,name,credit_card,vip) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, guest.getId());
            st.setString(2, guest.getName());
            st.setString(3, guest.getCreditCard());
            st.setBoolean(4, guest.isVip());
            st.executeUpdate();       
            
            ResultSet keyRS = st.getGeneratedKeys();
            guest.setId(getKey(keyRS,guest));
            
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting guest " + guest, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return guest;
    }

    private int getKey(ResultSet keyRS, Guest guest) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert guest " + guest
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            int result = keyRS.getInt(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert guest " + guest
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert guest " + guest
                    + " - no key found");
        }
    }
    
    private Guest resultSetToGuest(ResultSet rs) throws SQLException {
        Guest guest = new Guest();
        guest.setId(rs.getInt("id"));
        guest.setName(rs.getString("name"));
        guest.setCreditCard(rs.getString("credit_card"));
        guest.setVip(rs.getBoolean("vip"));
        return guest;
    }
    
    public Guest updateGuest(Guest guest) throws ServiceFailureException {
        
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO GUEST (id,name,credit_card,vip) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, guest.getId());
            st.setString(2, guest.getName());
            st.setString(3, guest.getCreditCard());
            st.setBoolean(4, guest.isVip());
            st.executeUpdate();       
            
            ResultSet keyRS = st.getGeneratedKeys();
            guest.setId(getKey(keyRS,guest));
            
            deleteGuest(guest);
            
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting guest " + guest, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return guest;
    }

    public Guest deleteGuest(Guest guest) throws ServiceFailureException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM GUEST WHERE id = ?");
            st.setInt(1, guest.getId());
            st.executeUpdate();       
            
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when deleting guest " + guest, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return guest;
    }

    public Collection<Guest> listAllGuests() throws ServiceFailureException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id,name,credit_card,vip FROM guest");
            ResultSet rs = st.executeQuery();
            
            List<Guest> result = new ArrayList<Guest>();
            while (rs.next()) {
                result.add(resultSetToGuest(rs));
            }
            return result;
            
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all guests", ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Guest findGuestById(int id) throws ServiceFailureException {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id,name,credit_card,vip FROM guest WHERE id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                Guest guest = resultSetToGuest(rs);

                if (rs.next()) {
                    throw new ServiceFailureException(
                            "Internal error: More entities with the same id found "
                            + "(source id: " + id + ", found " + guest + " and " + resultSetToGuest(rs));                    
                }            
                
                return guest;
            } else {
                return null;
                }
            
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving guest with id " + id, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Collection<Guest> findGuestsByName(String name) throws ServiceFailureException {
        PreparedStatement st = null;
        
        List<Guest> guests = new ArrayList<Guest>();
        
        try {
            st = conn.prepareStatement(
                    "SELECT id,name,credit_card,vip FROM guest WHERE name = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()) {
                guests.add(resultSetToGuest(rs));
            }
            return Collections.unmodifiableList(guests);
            
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving guests with name " + name, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
