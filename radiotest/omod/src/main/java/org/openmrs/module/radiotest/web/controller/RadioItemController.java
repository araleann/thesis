package org.openmrs.module.radiotest.web.controller;

import java.util.HashMap;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.api.RadioInventoryService;
import org.openmrs.module.radiotest.propertyeditor.RadioItemPropertyEditor;
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

@Controller
public class RadioItemController {

	private final String ITEM_FORM = "/module/radiotest/itemForm";
	private final String REDIRECT = "redirect:" + ITEM_FORM + ".htm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioItemType.class, new RadioItemTypePropertyEditor());
		binder.registerCustomEditor(RadioItem.class, new RadioItemPropertyEditor());
	}
	
	@ModelAttribute("item")
	public RadioItem getItem(){
		return new RadioItem();
	}
	
	@ModelAttribute("itemTypes")
	public List<RadioItemType> getAllTypes(){
		return Context.getService(RadioInventoryService.class).getAllItemTypes();
	}
	
	@ModelAttribute("items")
	public HashMap<RadioItemType, List<RadioItem>> getItemsByType(){
		RadioInventoryService is = Context.getService(RadioInventoryService.class);
		HashMap<RadioItemType, List<RadioItem>> map = new HashMap<RadioItemType, List<RadioItem>>();
		
		List<RadioItemType> itemTypes = is.getAllItemTypes();
		for(RadioItemType type : itemTypes){
			map.put(type, is.getItemByType(type));
		}
		
		return map;
	}
	
	@RequestMapping(value = ITEM_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@RequestMapping(value = ITEM_FORM, method = RequestMethod.POST)
	public ModelAndView saveItem(@ModelAttribute("item") RadioItem item, WebRequest request){
		
		Integer threshold = Integer.valueOf(request.getParameter("limit"));
		Integer type = Integer.valueOf(request.getParameter("limitType"));
		
		switch (type){
		case 1:
			item.setThreshold(threshold);
			break;
		case 2:
			item.setPercentThreshold(threshold);
			item.setThreshold(0);
			break;
		default:
			System.out.println("Threshold not set!");
		}
		
		Context.getService(RadioInventoryService.class).saveItem(item);
		
		return new ModelAndView(REDIRECT);
	}
	
	@RequestMapping(value = "/module/radiotest/nullItem", method = RequestMethod.POST)
	public ModelAndView nullItem(@RequestParam("iid") RadioItem item, @RequestParam("action") String action){
		RadioInventoryService is = Context.getService(RadioInventoryService.class);
		
		if (action.equalsIgnoreCase("void")){
			item.setVoided(!item.getVoided());
			is.saveItem(item);
		} else if (action.equalsIgnoreCase("delete")){
			is.deleteItem(item);
		}
		
		return new ModelAndView(REDIRECT);
	}
	
	@RequestMapping(value = "/module/radiotest/editItem", method = RequestMethod.POST)
	public ModelAndView editItem(@RequestParam("iid") RadioItem item, ModelMap model){
		
		model.addAttribute("itemTypes", Context.getService(RadioInventoryService.class).getAllItemTypes());
		model.addAttribute("item", item);
		
		return new ModelAndView(ITEM_FORM, model);
	}
}
