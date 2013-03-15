package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
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
public class RadioCategoryController {
	
	private final String CATEGORY_FORM = "/module/radiotest/categoryForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
	}
	
	@RequestMapping(value = CATEGORY_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("category")
	public RadioCategory getCategory(){
		return new RadioCategory();
	}
	
	@ModelAttribute("categories")
	public List<RadioCategory> getAllCategories(){
		return Context.getService(RadioPatientService.class).getAllCategories(true);
	}
	
	@RequestMapping(value = CATEGORY_FORM, method = RequestMethod.POST)
	public ModelAndView saveCategory(@ModelAttribute("category") RadioCategory category){
		Context.getService(RadioPatientService.class).saveCategory(category);
		
		return new ModelAndView("redirect:" + CATEGORY_FORM + ".htm");
	}
	
	@RequestMapping(value = "/module/radiotest/nullCategory", method = RequestMethod.POST)
	public ModelAndView voidCategory(@RequestParam("cid") RadioCategory category, @RequestParam("action") String action){
		RadioPatientService ps = Context.getService(RadioPatientService.class);
		if (action.equalsIgnoreCase("void")){
			category.setVoided(!category.getVoided());
			ps.saveCategory(category);
		} else if (action.equalsIgnoreCase("delete")){
			ps.deleteCategory(category);
		}
		
		return new ModelAndView("redirect:" + CATEGORY_FORM + ".htm");
	}
	
	@RequestMapping(value = "/module/radiotest/editCategory", method = RequestMethod.POST)
	public ModelAndView editCategory(@RequestParam("cid") RadioCategory category, ModelMap model){
		model.addAttribute("category", category);
		
		return new ModelAndView(CATEGORY_FORM, model);
	}
}
