package org.openmrs.module.radiotest.web.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.RadioStockListing;
import org.openmrs.module.radiotest.api.RadioInventoryService;
import org.openmrs.module.radiotest.model.RadioStockModel;
import org.openmrs.module.radiotest.propertyeditor.RadioComparator;
import org.openmrs.module.radiotest.propertyeditor.RadioItemTypePropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Controller
public class RadioStockController {
	
	private final String INVENTORY_PAGE = "/module/radiotest/inventory";
	private final String LISTINGS_PAGE = "/module/radiotest/listings";
	private final String STOCK_PAGE = "/module/radiotest/stock";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioItemType.class, new RadioItemTypePropertyEditor());
	}
	
	@ModelAttribute("itemTypes")
	public List<RadioItemType> getItemTypes(){
		return Context.getService(RadioInventoryService.class).getAllItemTypes();
	}
	
	@ModelAttribute("stockModel")
	public RadioStockModel getStockModel(){
		return new RadioStockModel();
	}
	
	@RequestMapping(value = INVENTORY_PAGE, method = RequestMethod.GET)
	public void showInventory(ModelMap model){
		RadioInventoryService is = Context.getService(RadioInventoryService.class);
		
		HashMap<RadioItemType, List<RadioItem>> itemMap = new HashMap<RadioItemType, List<RadioItem>>();
		List<RadioItemType> itemTypes = is.getAllItemTypes();
		for(RadioItemType type : itemTypes){
			itemMap.put(type, is.getItemByType(type));
		}
		
		model.addAttribute("items", itemMap);
	}
	
	@RequestMapping(value = LISTINGS_PAGE, method = RequestMethod.GET)
	public void showListings(ModelMap model){
		List<RadioStockListing> listings = Context.getService(RadioInventoryService.class).getAllListings();
		Collections.sort(listings, Collections.reverseOrder(new RadioComparator()));
		
		RadioStockListing latest = listings.get(0);
		model.addAttribute("listings", listings);
		model.addAttribute("updateDate", latest.getListingDate());
	}
	
	@RequestMapping(value = STOCK_PAGE, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@RequestMapping(value = "/module/radiotest/getItems", method = RequestMethod.POST)
	public ModelAndView getItemsByType(@RequestParam("tid") RadioItemType type, ModelMap model){
		List<RadioItem> itemList = Context.getService(RadioInventoryService.class).getItemByType(type);
		
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		json.addStaticAttribute("items", itemList);
		
		return new ModelAndView(json);
	}
	
	private String redirect(String url){
		return "redirect:" + url + ".htm";
	}
}
