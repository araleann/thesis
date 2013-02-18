package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.model.RadioTransactionModel;
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
public class RadioHomeController {
	
	private final String HOME = "/module/radiotest/home";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioTransaction.class, new RadioTransactionPropertyEditor());
	}
	
	@ModelAttribute("payment")
	public List<RadioTransaction> getUnpaid(){
		return Context.getService(RadioTransactionService.class).getPending("payment");
	}
	
	@ModelAttribute("results")
	public List<RadioTransaction> getPendingResults(){
		return Context.getService(RadioTransactionService.class).getPending("results");
	}
	
	@ModelAttribute("claim")
	public List<RadioTransaction> getUnclaimed(){
		return Context.getService(RadioTransactionService.class).getPending("claim");
	}
	
	@RequestMapping(value = HOME, method = RequestMethod.GET)
	public void showPending(HttpSession session){
		session.removeAttribute("patient");
	}
	
	@RequestMapping(value = "/module/radiotest/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewTransaction(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		model.addAttribute("transaction", trans);
		model.addAttribute("transModel", new RadioTransactionModel());
		
		return new ModelAndView("/module/radiotest/transactionForm", model);
	}
	
	@RequestMapping(value = "/module/radiotest/viewExams", method = RequestMethod.POST)
	public ModelAndView viewExams(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		System.out.println("viewExams");
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		System.out.println(trans.getId());
		model.addAttribute("trans", trans);
		
		return new ModelAndView("/module/radiotest/results", model);
	}
	
	@RequestMapping(value = "/module/radiotest/claimResults", method = RequestMethod.POST)
	public ModelAndView claimResults(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		trans.setClaimed(true);
		
		Context.getService(RadioTransactionService.class).saveTransaction(trans);
		
		return new ModelAndView("redirect:" + HOME + ".htm");
	}
}
