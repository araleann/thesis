package org.openmrs.module.radiotest.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioInventoryService;
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
public class RadioHomeController {
	
	private final String HOME = "/module/radiotest/home";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioTransaction.class, new RadioTransactionPropertyEditor());
	}
	
	@RequestMapping(value = HOME, method = RequestMethod.GET)
	public void showPending(HttpSession session, ModelMap model){
		session.removeAttribute("patient");
		RadioTransactionService ts = Context.getService(RadioTransactionService.class);
		List<RadioTransaction> unpaid = ts.getPending("payment");
		List<RadioTransaction> pending = ts.getPending("results");
		List<RadioTransaction> unclaimed = ts.getPending("claim");
		
		List<RadioTransaction> expired = new ArrayList<RadioTransaction>();
		Calendar currDate = new GregorianCalendar();
		
		for(RadioTransaction t : unpaid){
			if(t.isExpired(currDate)){
				expired.add(t);
			}
		}
		
		for(RadioTransaction t : pending){
			if(t.isExpired(currDate)){
				expired.add(t);
			}
		}
		
		for(RadioTransaction t : unclaimed){
			if(t.isExpired(currDate)){
				expired.add(t);
			}
		}
		
		unpaid.removeAll(expired);
		pending.removeAll(expired);
		unclaimed.removeAll(expired);
	
		model.addAttribute("items", Context.getService(RadioInventoryService.class).getAllItems());
		model.addAttribute("payment", unpaid);
		model.addAttribute("results", pending);
		model.addAttribute("claim", unclaimed);
		model.addAttribute("expired", expired);
	}
	
	@RequestMapping(value = "/module/radiotest/viewTransaction", method = RequestMethod.POST)
	public ModelAndView viewTransaction(@RequestParam("transId") RadioTransaction trans, HttpSession session, ModelMap model){
		session.setAttribute("transaction", trans);
		
		return new ModelAndView("redirect:/module/radiotest/transactionForm.htm");
	}
	
	@RequestMapping(value = "/module/radiotest/viewExams", method = RequestMethod.POST)
	public ModelAndView viewExams(@RequestParam("transId") RadioTransaction trans, HttpSession session, ModelMap model){
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		model.addAttribute("trans", trans);
		
		session.setAttribute("patient", trans.getPatient());
		return new ModelAndView("/module/radiotest/results", model);
	}
	
	@RequestMapping(value = "/module/radiotest/claimResults", method = RequestMethod.POST)
	public ModelAndView claimResults(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		trans = Context.getService(RadioTransactionService.class).updateTransaction(trans);
		trans.setClaimed(true);
		
		Context.getService(RadioTransactionService.class).saveTransaction(trans);
		
		return new ModelAndView("redirect:" + HOME + ".htm");
	}
	
	@RequestMapping(value = "/module/radiotest/voidTransaction", method = RequestMethod.POST)
	public ModelAndView viewTransaction(@RequestParam("transId") RadioTransaction trans, ModelMap model){
		RadioTransactionService ts = Context.getService(RadioTransactionService.class);
		trans = ts.updateTransaction(trans);
		trans.setVoided(true);
		
		ts.saveTransaction(trans);
		
		return new ModelAndView("redirect:" + HOME + ".htm");
	}
}
