/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class GuestManagerImpl implements GuestManager {

    List listOfGuests = new ArrayList<Guest>();
    
    public Guest createGuest(Guest guest) {
        listOfGuests.add(guest);
        return guest;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Guest deleteGuest(Guest guest) {
                listOfGuests.remove(guest);
                return guest;
    }

    public Collection<Guest> listAllGuests() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Guest findGuestById(int id) {
        Guest tempGuest;
        for(int i = 0; i < listOfGuests.size(); i++) {
            tempGuest = (Guest)listOfGuests.get(i);
            if(tempGuest.getId()==id) {
                return tempGuest;
            }
        }
        return null;
    }

    public Guest findGuestByName(String name) {
        Guest tempGuest;
        for(int i = 0; i < listOfGuests.size(); i++) {
            tempGuest = (Guest)listOfGuests.get(i);
            if(tempGuest.getName()==name) {
                return tempGuest;
            }
        }
        return null;
    }

    public Guest updateGuest(Guest guest) {
        Guest tempGuest = findGuestById(guest.getId());
        if(tempGuest == null) {
            return null;
        }
        deleteGuest(tempGuest);
        createGuest(guest);
        return guest;
    }   
}
