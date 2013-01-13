package org.openmrs.module.radiotest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RadioSearchFormController {

	private final String PATIENT_FORM = "/module/radiotest/searchPatientForm";
	
	@RequestMapping(value = PATIENT_FORM, method = RequestMethod.GET)
	public void showPatientForm(ModelMap model){
		
	}
}
