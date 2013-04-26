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
public class Guest {
	
	private Integer id;
	private String name;
	private String credit_card;
	private Boolean vip;

	public Guest() {}
	
	public Guest(Integer id, String name, String credit_card, Boolean vip) {
		this.id = id;
		this.name = name;
		this.credit_card = credit_card;
		this.vip = vip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCard() {
		return credit_card;
	}

	public void setCreditCard(String credit_card) {
		this.credit_card = credit_card;
	}

	public Boolean isVip() {
		return vip;
	}

	public void setVip(Boolean vip) {
		this.vip = vip;
	}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Guest other = (Guest) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.credit_card, other.credit_card)) {
            return false;
        }
        if (this.vip != other.vip) {
            return false;
        }
        return true;
    }
    
    
	
}
