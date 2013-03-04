package org.openmrs.module.radiotest.model;

import org.openmrs.module.radiotest.RadioStockListing;
import org.springframework.util.AutoPopulatingList;

public class RadioStockModel {

	private AutoPopulatingList<RadioStockListing> listings;
	
	public RadioStockModel(){
		listings = new AutoPopulatingList<RadioStockListing>(RadioStockListing.class);
	}

	public AutoPopulatingList<RadioStockListing> getListings() {
		return listings;
	}

	public void setListings(AutoPopulatingList<RadioStockListing> listings) {
		this.listings = listings;
	}
	
}
