/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel.gui;

import cz.muni.fi.pv168.hotel.App;
import cz.muni.fi.pv168.hotel.Guest;
import cz.muni.fi.pv168.hotel.GuestManager;
import javax.swing.table.AbstractTableModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author livthomas
 */
public class GuestTableModel extends AbstractTableModel {
 
    private GuestManager guestManager;

    public GuestTableModel() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class);        
        guestManager = ctx.getBean("guestManager", GuestManager.class);
    }
 
    @Override
    public int getRowCount() {
        return guestManager.listAllGuests().size();
    }
 
    @Override
    public int getColumnCount() {
        return 4;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Guest guest = guestManager.listAllGuests().get(rowIndex);
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
        Guest guest = guestManager.listAllGuests().get(rowIndex);
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
        guestManager.updateGuest(guest);
        fireTableCellUpdated(rowIndex, columnIndex);
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
    
}
