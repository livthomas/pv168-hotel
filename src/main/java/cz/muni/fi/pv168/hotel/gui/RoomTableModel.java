/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel.gui;

import cz.muni.fi.pv168.hotel.App;
import cz.muni.fi.pv168.hotel.Room;
import cz.muni.fi.pv168.hotel.RoomManager;
import cz.muni.fi.pv168.hotel.RoomType;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author livthomas
 */
public class RoomTableModel extends AbstractTableModel {
    private static final ResourceBundle texts = ResourceBundle.getBundle("texts");
    
    protected ApplicationContext ctx;
 
    protected RoomManager roomManager;
    
    protected List<Room> rooms = new ArrayList<>();

    public RoomTableModel() {
        ctx = new AnnotationConfigApplicationContext(App.SpringConfig.class); 
        roomManager = ctx.getBean(texts.getString("ROOMMANAGER"), RoomManager.class);
        
        RetrieveSwingWorker retrieveSwingWorker = new RetrieveSwingWorker();
        retrieveSwingWorker.execute();
    }
    
    private class CreateSwingWorker extends SwingWorker<Void,Void> {
        private Room room;
        
        public CreateSwingWorker(Room room) {
            this.room = room;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            roomManager.createRoom(room);
            rooms.add(room);
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
            rooms = roomManager.listAllRooms();
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableRowsInserted(0, getRowCount() - 1);
        }
    }
    
    private class UpdateSwingWorker extends SwingWorker<Void,Void> {
        private Room room;
        private int rowIndex;
        private int columnIndex;
        
        public UpdateSwingWorker(Room room, int rowIndex, int columnIndex) {
            this.room = room;
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }
        
        @Override    
        protected Void doInBackground() throws Exception {
            roomManager.updateRoom(room);
            rooms.set(rowIndex, room);
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
            Integer id = rooms.get(row).getId();
            rooms.remove(row);
            roomManager.deleteRoom(id);
            return null;
        }
        
        @Override    
        protected void done() {
            fireTableRowsDeleted(row, row);
        }
    }
 
    @Override
    public int getRowCount() {
        return rooms.size();
    }
 
    @Override
    public int getColumnCount() {
        return 5;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
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
                throw new IllegalArgumentException(texts.getString("COLUMNINDEX"));
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return texts.getString("ID");
            case 1:
                return texts.getString("TYPE");
            case 2:
                return texts.getString("NUMBER OF BEDS");
            case 3:
                return texts.getString("VIEW OF SEA");
            case 4:
                return texts.getString("NOTE");
            default:
                throw new IllegalArgumentException(texts.getString("COLUMNINDEX"));
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
                throw new IllegalArgumentException(texts.getString("COLUMNINDEX"));
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
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
                throw new IllegalArgumentException(texts.getString("COLUMNINDEX"));
        }
        UpdateSwingWorker updateSwingWorker = new UpdateSwingWorker(room, rowIndex, columnIndex);
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
            case 4:
                return true;
            default:
                throw new IllegalArgumentException(texts.getString("COLUMNINDEX"));
        }
    }
    
    public void addRow(Room room) {
        CreateSwingWorker createSwingWorker = new CreateSwingWorker(room);
        createSwingWorker.execute();
    }
    
    public void removeRow(int row) {
        DeleteSwingWorker deleteSwingWorker = new DeleteSwingWorker(row);
        deleteSwingWorker.execute();
    }
    
}
