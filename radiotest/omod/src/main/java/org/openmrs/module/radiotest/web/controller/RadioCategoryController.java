package org.openmrs.module.radiotest.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RadioCategoryController {
	
	private final String CATEGORY_FORM = "/module/radiotest/categoryForm";
	
	@RequestMapping(value = CATEGORY_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("category")
	public RadioCategory getCategory(){
		return new RadioCategory();
	}
	
	@RequestMapping(value = CATEGORY_FORM, method = RequestMethod.POST)
	public void saveCategory(@ModelAttribute("category") RadioCategory category){
		Context.getService(RadioPatientService.class).saveCategory(category);
	}
}
