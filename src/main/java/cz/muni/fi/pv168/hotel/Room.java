/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Objects;

/**
 *
 * @author livthomas
 */
public class Room {
	
	private Integer id;
	private RoomType type;
	private Short beds;
	private Boolean seaView;
	private String note;
	
	public Room() {}

	public Room(Integer id, RoomType type, Short beds, Boolean seaView, String note) {
		this.id = id;
		this.type = type;
		this.beds = beds;
		this.seaView = seaView;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Short getBeds() {
		return beds;
	}

	public void setBeds(Short beds) {
		this.beds = beds;
	}

	public Boolean getSeaView() {
		return seaView;
	}

	public void setSeaView(Boolean seaView) {
		this.seaView = seaView;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.beds, other.beds)) {
            return false;
        }
        if (!Objects.equals(this.seaView, other.seaView)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }
    
}
