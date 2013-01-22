package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.association.RadioCategoryExam;
import org.openmrs.module.radiotest.model.RadioExamModel;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioExamTypePropertyEditor;
import org.openmrs.module.radiotest.type.RadioCategory;
import org.openmrs.module.radiotest.type.RadioExamType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
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
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
	}
	
	@RequestMapping(value = EXAM_FORM, method = RequestMethod.GET)
	public void showExamForm(){
	}
	
	@ModelAttribute("categories")
	public List<RadioCategory> getCategories(){
		return Context.getService(RadioPatientService.class).getAllCategories();
	}
	
	@ModelAttribute("types")
	public List<RadioExamType> getExamTypes() {
		return Context.getService(RadioExamService.class).getAllExamTypes();
	}
	
	@ModelAttribute("examModel")
	public RadioExamModel getExamModel(){
		RadioExamModel examModel = new RadioExamModel();
		examModel.setExam(new RadioExam());
		examModel.setCategoryFees(new AutoPopulatingList<RadioCategoryExam>(RadioCategoryExam.class));
		
		return examModel;
	}
	
	@RequestMapping(value = EXAM_FORM, method = RequestMethod.POST)
	public void saveExam(@ModelAttribute("examModel") RadioExamModel examModel, ModelMap model){
		RadioExam exam = examModel.getFullExam();
		Context.getService(RadioExamService.class).saveExam(exam);
	}
}
