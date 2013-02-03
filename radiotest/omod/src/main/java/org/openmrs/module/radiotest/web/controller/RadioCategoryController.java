package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@ModelAttribute("categories")
	public List<RadioCategory> getAllCategories(){
		return Context.getService(RadioPatientService.class).getAllCategories();
	}
	
	@RequestMapping(value = CATEGORY_FORM, method = RequestMethod.POST)
	public ModelAndView saveCategory(@ModelAttribute("category") RadioCategory category){
		Context.getService(RadioPatientService.class).saveCategory(category);
		
		return new ModelAndView("redirect:" + CATEGORY_FORM + ".htm");
	}
}
