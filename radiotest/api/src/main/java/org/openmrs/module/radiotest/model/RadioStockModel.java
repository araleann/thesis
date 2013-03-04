package org.openmrs.module.radiotest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.module.radiotest.RadioItem;
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
	
	public List<RadioStockListing> getFullListings(){
		Date date = new Date();
		
		for(RadioStockListing list : listings){
			list.setListingDate(date);
		}
		
		return listings;
	}
}
