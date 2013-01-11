package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.api.RadioTestService;
import org.openmrs.module.radiotest.model.RadioPatientModel;
import org.openmrs.module.radiotest.propertyeditor.RadioAliasPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RadioPatientListFormController {

	private final String LISTPATIENT_FORM = "/module/radiotest/listPatient";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
		binder.registerCustomEditor(RadioAlias.class, new RadioAliasPropertyEditor());
	}
	
	@RequestMapping(value = LISTPATIENT_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("patients")
	public List<RadioPatient> getPatientName(){
		return Context.getService(RadioTestService.class).getAllPatients(true);
	}
	
	@ModelAttribute("patientModel")
	public RadioPatientModel getPatientModel(){
		return new RadioPatientModel();
	}
	
	@RequestMapping(value = LISTPATIENT_FORM, method = RequestMethod.POST)
	public ModelAndView getPatientFromForm(@ModelAttribute("patientModel") RadioPatientModel model, WebRequest request){
		RadioPatient patient = model.getFullPatient();
		try {
			Context.getService(RadioTestService.class).savePatient(patient);
		} catch (Exception ex) {
			System.out.println("Exception!");
		}
		return new ModelAndView("/module/radiotest/success", "patient", patient);
	}
}
 