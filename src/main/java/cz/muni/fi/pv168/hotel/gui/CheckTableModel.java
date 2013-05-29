/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel.gui;

import cz.muni.fi.pv168.hotel.Guest;
import cz.muni.fi.pv168.hotel.HotelManager;
import cz.muni.fi.pv168.hotel.Room;
import java.util.ResourceBundle;
import javax.swing.SwingWorker;

/**
 *
 * @author livthomas
 */
public class CheckTableModel extends RoomTableModel {
    private static final ResourceBundle texts = ResourceBundle.getBundle("texts");
    
    private HotelManager hotelManager;
    
    private Guest guest;

    public CheckTableModel(Guest guest) {
        super();
        this.guest = guest;
        hotelManager = ctx.getBean(texts.getString("HOTELMANAGER"), HotelManager.class);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    private class CheckInSwingWorker extends SwingWorker<Void,Void> {
        private Room room;
        
        public CheckInSwingWorker(Room room) {
            this.room = room;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            hotelManager.checkIn(guest, room);
            return null;
        }
    }
    
    private class CheckOutSwingWorker extends SwingWorker<Void,Void> {        
        @Override    
        protected Void doInBackground() throws Exception {
            hotelManager.checkOut(guest);
            return null;
        }
    }
    
    public void checkIn(int row) {
        Room room = rooms.get(row);
        CheckInSwingWorker checkInSwingWorker = new CheckInSwingWorker(room);
        checkInSwingWorker.execute();
    }
    
    public void checkOut() {
        CheckOutSwingWorker checkOutSwingWorker = new CheckOutSwingWorker();
        checkOutSwingWorker.execute();
    }
    
    public boolean isChecked() {
        return hotelManager.findRoomByGuest(guest) != null;
    }
    
}
