package org.openmrs.module.radiotest.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.propertyeditor.RadioExamTypePropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RadioExamController {

	private final String EXAM_FORM = "/module/radiotest/examForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioExamType.class, new RadioExamTypePropertyEditor());
	}
	
	@RequestMapping(value = EXAM_FORM)
	public void showExamForm(Model model){
		// populate model with existing exams
		
		model.addAttribute("types", Context.getService(RadioExamService.class).getAllExamTypes());
	}
	
	@ModelAttribute("exam")
	public RadioExam getExam(){
		return new RadioExam();
	}
	
	@RequestMapping(value = EXAM_FORM, method = RequestMethod.POST)
	public void saveExam(ModelMap modelMap, @ModelAttribute RadioExam exam){
		Context.getService(RadioExamService.class).saveExam(exam);
	}
}
