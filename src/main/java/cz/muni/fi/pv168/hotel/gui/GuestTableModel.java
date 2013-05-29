/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel.gui;

import cz.muni.fi.pv168.hotel.App;
import cz.muni.fi.pv168.hotel.Guest;
import cz.muni.fi.pv168.hotel.GuestManager;
import cz.muni.fi.pv168.hotel.HotelManager;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author livthomas
 */
public class GuestTableModel extends AbstractTableModel {
 
    private GuestManager guestManager;
    private HotelManager hotelManager;
    
    private List<Guest> guests = new ArrayList<>();

    public GuestTableModel() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class);        
        guestManager = ctx.getBean("guestManager", GuestManager.class);    
        hotelManager = ctx.getBean("hotelManager", HotelManager.class);
        
        RetrieveSwingWorker retrieveSwingWorker = new RetrieveSwingWorker();
        retrieveSwingWorker.execute();
    }
    
    private class CreateSwingWorker extends SwingWorker<Void,Void> {
        private Guest guest;
        
        public CreateSwingWorker(Guest guest) {
            this.guest = guest;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            guestManager.createGuest(guest);
            guests.add(guest);
            return null;
        }
        
        @Override    
        protected void done() {
            int lastRow = getRowCount() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
    }
    
    private class RetrieveSwingWorker extends SwingWorker<Void,Void> {        
        @Override    
        protected Void doInBackground() throws Exception {
            guests = guestManager.listAllGuests();
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableRowsInserted(0, getRowCount() - 1);
        }
    }
    
    private class UpdateSwingWorker extends SwingWorker<Void,Void> {
        private Guest guest;
        private int rowIndex;
        private int columnIndex;
        
        public UpdateSwingWorker(Guest guest, int rowIndex, int columnIndex) {
            this.guest = guest;
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            guestManager.updateGuest(guest);
            guests.set(rowIndex, guest);
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
    private class DeleteSwingWorker extends SwingWorker<Void,Void> {        
        private int row;
        
        public DeleteSwingWorker(int row) {
            this.row = row;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            Integer id = guests.get(row).getId();
            guests.remove(row);
            guestManager.deleteGuest(id);
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableRowsDeleted(row, row);
        }
    }
    
//    public class ColorCellRenderer extends DefaultTableCellRenderer { 
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            if (hotelManager.findRoomByGuest(guests.get(row)) != null) {
//                setBackground(Color.CYAN);
//            }
//            return this;
//        }
//    }
 
    @Override
    public int getRowCount() {
        return guests.size();
    }
 
    @Override
    public int getColumnCount() {
        return 4;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Guest guest = guests.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return guest.getId();
            case 1:
                return guest.getName();
            case 2:
                return guest.getCreditCard();
            case 3:
                return guest.getVip();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "Name";
            case 2:
                return "Credit card";
            case 3:
                return "VIP";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
                return String.class;
            case 3:
                return Boolean.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Guest guest = guests.get(rowIndex); // swingworker
        switch (columnIndex) {
            case 0:
                guest.setId((Integer) aValue);
                break;
            case 1:
                guest.setName((String) aValue);
                break;
            case 2:
                guest.setCreditCard((String) aValue);
                break;
            case 3:
                guest.setVip((Boolean) aValue);
                break;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
        UpdateSwingWorker updateSwingWorker = new UpdateSwingWorker(guest, rowIndex, columnIndex);
        updateSwingWorker.execute();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
            case 2:
            case 3:
                return true;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    public void addRow(Guest guest) {
        CreateSwingWorker createSwingWorker = new CreateSwingWorker(guest);
        createSwingWorker.execute();
    }
    
    public void removeRow(int row) {
        DeleteSwingWorker deleteSwingWorker = new DeleteSwingWorker(row);
        deleteSwingWorker.execute();
    }
    
    public Guest getRow(int row) {
        return guests.get(row);
    }
    
}
