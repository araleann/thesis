package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioResult;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioTransExamPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioTransactionPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		binder.registerCustomEditor(RadioTransExam.class, new RadioTransExamPropertyEditor());
	}
	
	@RequestMapping(value = RESULTS_PAGE, method = RequestMethod.GET)
	public void showTransactions(ModelMap model){
		RadioPatient patient = Context.getService(RadioPatientService.class).getPatient(new Integer(1));
		List<RadioTransaction> transList = Context.getService(RadioTransactionService.class).getTransactions(patient);
		
		model.addAttribute("transList", transList);
	}
	
	@RequestMapping(value = "/module/radiotest/getExamList", method = RequestMethod.POST)
	public ModelAndView getExamList(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		model.addAttribute("exams", trans.getExams());
		
		return new ModelAndView("/module/radiotest/examList", model);
	}
	
	@ModelAttribute("result")
	public RadioResult getResult(){
		return new RadioResult();
	}
	
	@RequestMapping(value = RESULTS_PAGE, method = RequestMethod.POST)
	public ModelAndView editExamResults(@RequestParam("examId") RadioTransExam e, WebRequest request, ModelMap model){
		model.addAttribute("transExam", e);
		model.addAttribute("count", request.getParameter("count"));
		if(e.hasResult()){
			model.addAttribute("result", e.getResult());
		}
		
		return new ModelAndView("/module/radiotest/resultsForm", model);
	}
	
	@RequestMapping(value = RESULTS_FORM, method = RequestMethod.POST)
	public ModelAndView saveResult(@ModelAttribute("result") RadioResult result, @RequestParam("examId") RadioTransExam e, 
								ModelMap model){
		if(!result.isDraft()){
			e.setPending(false);
		}
		e.addFinding(result);
		e.getTransaction().update();
		model.addAttribute("examId", e.getId());
		
		Context.getService(RadioTransactionService.class).saveTransExam(e);
		
		return new ModelAndView("/module/radiotest/editResult", model);
	}
	
	@RequestMapping(value = "/module/radiotest/editResultForm", method = RequestMethod.POST)
	public ModelAndView editResult(@RequestParam("examId") Integer examId, ModelMap model){
		model.addAttribute("examId", examId);
		model.addAttribute("result", new RadioResult());
		
		return new ModelAndView("/module/radiotest/editResult", model);
	}
}
