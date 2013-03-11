package org.openmrs.module.radiotest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.module.radiotest.RadioExamItem;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioStockListing;
import org.springframework.util.AutoPopulatingList;

public class RadioStockModel {

	private AutoPopulatingList<RadioStockListing> listings;
	private List<RadioItem> items;
	
	private AutoPopulatingList<RadioExamItem> examItems;
	
	public RadioStockModel(){
		listings = new AutoPopulatingList<RadioStockListing>(RadioStockListing.class);
		items = new ArrayList<RadioItem>();
		
		examItems = new AutoPopulatingList<RadioExamItem>(RadioExamItem.class);
	}

	public AutoPopulatingList<RadioStockListing> getListings() {
		return listings;
	}

	public void setListings(AutoPopulatingList<RadioStockListing> listings) {
		this.listings = listings;
	}
	
	public List<RadioItem> getItems() {
		return items;
	}

	public void setItems(List<RadioItem> items) {
		this.items = items;
	}

	public AutoPopulatingList<RadioExamItem> getExamItems() {
		return examItems;
	}

	public void setExamItems(AutoPopulatingList<RadioExamItem> examItems) {
		this.examItems = examItems;
	}

	public List<RadioStockListing> getFullListings(){
		Date date = new Date();
		
		for(RadioStockListing list : listings){
			list.setListingDate(date);
		}
		
		return listings;
	}
}
