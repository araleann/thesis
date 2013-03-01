package org.openmrs.module.radiotest;

import java.util.Set;

import org.openmrs.BaseOpenmrsData;

public class RadioItem extends BaseOpenmrsData {

	private Integer id;
	private RadioItemType type;
	
	private String name;
	private String unit;
	private boolean atLimit = false;
	
	private int quantity;
	private int threshold;
	private int percentThreshold;
	
	private Set<RadioStockListing> stockListings;
	
	private Boolean voided = false;
	
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

	public RadioItemType getType() {
		return type;
	}

	public void setType(RadioItemType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean isAtLimit() {
		return atLimit;
	}

	public void setAtLimit(boolean atLimit) {
		this.atLimit = atLimit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getPercentThreshold() {
		return percentThreshold;
	}

	public void setPercentThreshold(int percentThreshold) {
		this.percentThreshold = percentThreshold;
	}

	public Set<RadioStockListing> getStockListings() {
		return stockListings;
	}

	public void setStockListings(Set<RadioStockListing> stockListings) {
		this.stockListings = stockListings;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	// CUSTOM FUNCTIONS
	public void addStock(int qty){
		quantity = quantity + qty;
	}
	
	public void updateStock(int qty){
		quantity = quantity - qty;
	}
}
