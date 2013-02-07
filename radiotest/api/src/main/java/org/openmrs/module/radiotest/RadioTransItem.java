package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioTransItem extends BaseOpenmrsData {

	private Integer id;
	private RadioTransaction transaction;
	private RadioItem item;
	
	private int quantity;
	private boolean logged;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	public RadioItem getItem() {
		return item;
	}

	public void setItem(RadioItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	// CUSTOM FUNCTIONS
	public void log(){
		item.updateStock(quantity);
	}
}
