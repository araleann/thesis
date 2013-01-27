package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioTransactionPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RadioResultsController {
	
	private final String RESULTS_PAGE = "/module/radiotest/results";
	private final String RESULTS_FORM = "/module/radiotest/resultsForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioTransaction.class, new RadioTransactionPropertyEditor());
	}
	
	@RequestMapping(value = RESULTS_PAGE, method = RequestMethod.GET)
	public void showTransactions(ModelMap model){
		RadioPatient patient = Context.getService(RadioPatientService.class).getPatient(new Integer(1));
		List<RadioTransaction> transList = Context.getService(RadioTransactionService.class).getTransactions(patient);
		
		model.addAttribute("transList", transList);
	}
	
	@RequestMapping(value = "/module/radiotest/getExamList", method = RequestMethod.POST)
	public ModelAndView getExamList(@RequestParam("transId") Integer transId, ModelMap model){
		RadioTransaction trans = Context.getService(RadioTransactionService.class).getTransaction(transId);
		model.addAttribute("exams", trans.getExams());
		
		return new ModelAndView("/module/radiotest/examList", model);
	}
}
