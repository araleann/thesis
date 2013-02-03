package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RadioExamTypeController {
	
	private final String EXAM_TYPE_FORM = "/module/radiotest/examTypeForm";
	
	@RequestMapping(value = EXAM_TYPE_FORM, method = RequestMethod.GET)
	public void showExamTypeForm(Model model){
		// populate model with existing exam types
	}
	
	@ModelAttribute("examType")
	public RadioExamType getExamType(){
		return new RadioExamType();
	}
	
	@ModelAttribute("examTypes")
	public List<RadioExamType> getExamTypes(){
		return Context.getService(RadioExamService.class).getAllExamTypes();
	}
	
	@RequestMapping(value = EXAM_TYPE_FORM, method = RequestMethod.POST)
	public ModelAndView saveExamType(ModelMap modelMap, @ModelAttribute RadioExamType examType){
		Context.getService(RadioExamService.class).saveExamType(examType);
		
		return new ModelAndView("redirect:" + EXAM_TYPE_FORM + ".htm");
	}
}
