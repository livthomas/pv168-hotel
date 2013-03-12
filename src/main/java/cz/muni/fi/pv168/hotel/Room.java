/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

/**
 *
 * @author livthomas
 */
public class Room {
	
	private int id;
	private RoomType type;
	private short beds;
	private boolean seaView;
	private String note;
	
	public Room() {}

	public Room(int id, RoomType type, short beds, boolean seaView, String note) {
		this.id = id;
		this.type = type;
		this.beds = beds;
		this.seaView = seaView;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public short getBeds() {
		return beds;
	}

	public void setBeds(short beds) {
		this.beds = beds;
	}

	public boolean isSeaView() {
		return seaView;
	}

	public void setSeaView(boolean seaView) {
		this.seaView = seaView;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
