package org.openmrs.module.radiotest.web.controller;

import java.util.Date;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.model.RadioTransactionModel;
import org.openmrs.module.radiotest.propertyeditor.RadioExamPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioExamTypePropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioNoteTypePropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioPatientPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioTransactionPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("transaction")
public class RadioTransactionController {

	private final String TRANS_EXAM_FORM = "/module/radiotest/transExamForm";
	private final String TRANSACTION_FORM = "/module/radiotest/transactionForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioExam.class, new RadioExamPropertyEditor());
		binder.registerCustomEditor(RadioExamType.class, new RadioExamTypePropertyEditor());
		binder.registerCustomEditor(RadioPatient.class, new RadioPatientPropertyEditor());
		binder.registerCustomEditor(RadioNoteType.class, new RadioNoteTypePropertyEditor());
		binder.registerCustomEditor(RadioTransaction.class, new RadioTransactionPropertyEditor());
	}
	
	@RequestMapping(value = TRANS_EXAM_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("transModel")
	public RadioTransactionModel getModel(){
		return new RadioTransactionModel();
	}
	
	@ModelAttribute("examTypes")
	public List<RadioExamType> getExamTypes(){
		return Context.getService(RadioExamService.class).getAllExamTypes();
	}
	
	@ModelAttribute("noteTypes")
	public List<RadioNoteType> getNoteTypes(){
		return Context.getService(RadioTransactionService.class).getAllNoteTypes();
	}
	
	@RequestMapping(value = "/module/radiotest/addExam", method = RequestMethod.POST)
	public ModelAndView addExam(@RequestParam("index") Integer index, ModelMap model){
		model.addAttribute("index", index);
		
		return new ModelAndView("/module/radiotest/editExam", model);
	}
	
	@RequestMapping(value = "/module/radiotest/getExams", method = RequestMethod.POST)
	public ModelAndView getExamByType(@RequestParam("type") RadioExamType type, WebRequest request, ModelMap model){
		model.addAttribute("index", request.getParameter("index"));
		model.addAttribute("exam", Context.getService(RadioExamService.class).getExamByType(type));
		
		return new ModelAndView("/module/radiotest/editExam", model);
	}
	
	@RequestMapping(value = TRANS_EXAM_FORM, method = RequestMethod.POST)
	public ModelAndView saveTransaction(@ModelAttribute("transModel") RadioTransactionModel tm, ModelMap model){
		RadioTransaction trans = tm.getFullTransaction();
		Date d = new Date();
		trans.setVisitDate(d);
		trans.setVisitTime(d);
		
		model.addAttribute("transaction", Context.getService(RadioTransactionService.class).saveTransaction(trans));
		
		return new ModelAndView(TRANSACTION_FORM, model);
	}
	
	@RequestMapping(value = "/module/radiotest/saveNote", method = RequestMethod.POST)
	public ModelAndView saveNote(@ModelAttribute("transModel") RadioTransactionModel tm, ModelMap model){
		tm.setTransaction(Context.getService(RadioTransactionService.class).updateTransaction(tm.getTransaction()));
		RadioTransaction trans = tm.getFullTransaction();
		Context.getService(RadioTransactionService.class).saveTransaction(trans);
		
		return new ModelAndView("/module/radiotest/editNote", model);
	}
	
	@RequestMapping(value = TRANSACTION_FORM, method = RequestMethod.GET)
	public void showTransaction(){
		
	}
	
	@RequestMapping(value = TRANSACTION_FORM, method = RequestMethod.POST)
	public void savePayment(@RequestParam("transId") RadioTransaction trans, 
								WebRequest request, ModelMap model){
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		trans.setOrNumber(request.getParameter("orNumber"));
		trans.setPaid(true);
		
		Context.getService(RadioTransactionService.class).saveTransaction(trans);
	}
}
