package org.openmrs.module.radiotest.web.controller;

import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.propertyeditor.RadioExamPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
}
