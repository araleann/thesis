package org.openmrs.module.radiotest;

import java.util.Date;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioStockListing extends BaseOpenmrsMetadata {

	private Integer id;
	private RadioItem item;
	
	private Date listingDate;
	private int quantity;
	
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

	public RadioItem getItem() {
		return item;
	}

	public void setItem(RadioItem item) {
		this.item = item;
	}

	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
