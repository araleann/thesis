package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.model.RadioTransactionModel;
import org.openmrs.module.radiotest.propertyeditor.RadioExamPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RadioTransactionController {

	private final String TRANSACTION_FORM = "/module/radiotest/transactionForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioExam.class, new RadioExamPropertyEditor());
	}
	
	@RequestMapping(value = TRANSACTION_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("transModel")
	public RadioTransactionModel getModel(){
		RadioTransactionModel model = new RadioTransactionModel();
		model.setExams(new AutoPopulatingList<RadioTransExam>(RadioTransExam.class));
		
		return model;
	}
	
	@ModelAttribute("examTypes")
	public List<RadioExamType> getExamTypes(){
		return Context.getService(RadioExamService.class).getAllExamTypes();
	}
	
	@ModelAttribute("type")
	public RadioExamType getType(){
		return new RadioExamType();
	}
	
}
