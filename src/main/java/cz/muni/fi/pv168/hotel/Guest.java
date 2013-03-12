/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

/**
 *
 * @author livthomas
 */
public class Guest {
	
	private int id;
	private String name;
	private String creditCard;
	private boolean vip;

	public Guest() {}
	
	public Guest(int id, String name, String creditCard, boolean vip) {
		this.id = id;
		this.name = name;
		this.creditCard = creditCard;
		this.vip = vip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
}
