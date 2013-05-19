/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel.gui;

import cz.muni.fi.pv168.hotel.App;
import cz.muni.fi.pv168.hotel.Room;
import cz.muni.fi.pv168.hotel.RoomManager;
import cz.muni.fi.pv168.hotel.RoomType;
import javax.swing.table.AbstractTableModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author livthomas
 */
public class RoomTableModel extends AbstractTableModel {
 
    private RoomManager roomManager;

    public RoomTableModel() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class); 
        roomManager = ctx.getBean("roomManager", RoomManager.class);
    }    
 
    @Override
    public int getRowCount() {
        return roomManager.listAllRooms().size();
    }
 
    @Override
    public int getColumnCount() {
        return 5;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = roomManager.listAllRooms().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return room.getId();
            case 1:
                return room.getType();
            case 2:
                return room.getBeds();
            case 3:
                return room.getSeaView();
            case 4:
                return room.getNote();
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
                return "Type";
            case 2:
                return "Number of beds";
            case 3:
                return "View of sea";
            case 4:
                return "Note";
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
                return RoomType.class;
            case 2:
                return Short.class;
            case 3:
                return Boolean.class;
            case 4:
                return String.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Room room = roomManager.listAllRooms().get(rowIndex);
        switch (columnIndex) {
            case 0:
                room.setId((Integer) aValue);
                break;
            case 1:
                room.setType((RoomType) aValue);
                break;
            case 2:
                room.setBeds((Short) aValue);
                break;
            case 3:
                room.setSeaView((Boolean) aValue);
                break;
            case 4:
                room.setNote((String) aValue);
                break;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
        roomManager.updateRoom(room);
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
            case 4:
                return true;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
}
