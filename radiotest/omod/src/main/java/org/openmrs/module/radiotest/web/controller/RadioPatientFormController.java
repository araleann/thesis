package org.openmrs.module.radiotest.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.model.RadioPatientModel;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("patient")
public class RadioPatientFormController {

	private final String PATIENT_FORM = "/module/radiotest/patientForm";
	private final String PATIENT_PROFILE = "/module/radiotest/patientProfile";
	private final String EDIT_PATIENT = "/module/radiotest/editPatient";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
	}
	
	@ModelAttribute("categories")
	public List<RadioCategory> getCategories(){
		return Context.getService(RadioPatientService.class).getAllCategories();
	}
	
	@ModelAttribute("patientModel")
	public RadioPatientModel getPatientModel(){
		return new RadioPatientModel();
	}
	
	@RequestMapping(value = PATIENT_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@RequestMapping(value = PATIENT_PROFILE, method = RequestMethod.GET)
	public void showProfile(HttpSession session, ModelMap model){
		RadioPatient patient = (RadioPatient) session.getAttribute("patient");
		patient = Context.getService(RadioPatientService.class).updatePatient(patient);
		
		model.addAttribute("patient", patient);
	}
	
	@RequestMapping(value = {PATIENT_FORM, EDIT_PATIENT}, method = RequestMethod.POST)
	public ModelAndView savePatient(@ModelAttribute("patientModel") RadioPatientModel pm, 
												WebRequest request, HttpSession session, ModelMap model){
		RadioPatient patient = (RadioPatient) model.get("patient");
		
		if(patient != null){
			pm.setPatient(patient);	
		}
		
		patient = pm.getFullPatient();
		patient.setUpdateDate(new Date());
		session.setAttribute("patient", Context.getService(RadioPatientService.class).savePatient(patient));
		
		return new ModelAndView("redirect:/module/radiotest/transExamForm.htm");
	}
}
 