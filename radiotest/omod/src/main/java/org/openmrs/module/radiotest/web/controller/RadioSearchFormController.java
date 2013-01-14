package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RadioSearchFormController {

	private final String PATIENT_FORM = "/module/radiotest/searchPatientForm";
	
	@RequestMapping(value = PATIENT_FORM, method = RequestMethod.GET)
	public void showPatientForm(){
		
	}
	
	@RequestMapping(value = PATIENT_FORM, method = RequestMethod.POST)
	public void searchPatient(@RequestParam String search, ModelMap model){
		List<RadioPatient> list = Context.getService(RadioPatientService.class).search(search);
		System.out.println(list.size());
		model.addAttribute("result", list);
	}
}
