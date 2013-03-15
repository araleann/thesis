package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.api.RadioInventoryService;
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
public class RadioItemTypeController {

	private final String ITEM_TYPE_FORM = "/module/radiotest/itemTypeForm";
	private final String REDIRECT = "redirect:" + ITEM_TYPE_FORM + ".htm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioItemType.class, new RadioItemTypePropertyEditor());
	}
	
	@ModelAttribute("itemType")
	public RadioItemType getItemType(){
		return new RadioItemType();
	}
	
	@ModelAttribute("itemTypes")
	public List<RadioItemType> getAllItemTypes(){
		return Context.getService(RadioInventoryService.class).getAllItemTypes(true);
	}
	
	@RequestMapping(value = ITEM_TYPE_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@RequestMapping(value = ITEM_TYPE_FORM, method = RequestMethod.POST)
	public ModelAndView saveItemType(@ModelAttribute("itemType") RadioItemType type){
		Context.getService(RadioInventoryService.class).saveItemType(type);
		
		return new ModelAndView(REDIRECT);
	}
	
	
	@RequestMapping(value = "/module/radiotest/nullItemType", method = RequestMethod.POST)
	public ModelAndView nullItemType(@RequestParam("tid") RadioItemType type, @RequestParam("action") String action){
		RadioInventoryService is = Context.getService(RadioInventoryService.class);
		if (action.equalsIgnoreCase("void")){
			type.setVoided(!type.getVoided());
			is.saveItemType(type);
		} else if (action.equalsIgnoreCase("delete")){
			System.out.println("DELETE");
			is.deleteItemType(type);
		}
		
		return new ModelAndView(REDIRECT);
	}
	
	@RequestMapping(value = "/module/radiotest/editItemType", method = RequestMethod.POST)
	public ModelAndView editItemType(@RequestParam("tid") RadioItemType type, ModelMap model){
		model.addAttribute("itemType", type);
		System.out.println(type.getName());
		return new ModelAndView(ITEM_TYPE_FORM, model);
	}
}
