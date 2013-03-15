package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioFeeType;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioFeeTypePropertyEditor;
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
public class RadioFeeTypeController {

	private final String FEE_TYPE_FORM = "/module/radiotest/feeTypeForm";
	private final String REDIRECT_FORM = "redirect:" + FEE_TYPE_FORM + ".htm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioFeeType.class, new RadioFeeTypePropertyEditor());
	}
	
	@RequestMapping(value = FEE_TYPE_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("feeType")
	public RadioFeeType getFeeType(){
		return new RadioFeeType();
	}
	
	@ModelAttribute("feeTypes")
	public List<RadioFeeType> getAllFeeTypes(){
		return Context.getService(RadioTransactionService.class).getAllFeeTypes(true);
	}
	
	@RequestMapping(value = FEE_TYPE_FORM, method = RequestMethod.POST)
	public ModelAndView saveFeeType(@ModelAttribute("feeType") RadioFeeType type, ModelMap model){
		Context.getService(RadioTransactionService.class).saveFeeType(type);
		
		return new ModelAndView(REDIRECT_FORM);
	}
	
	@RequestMapping(value = "/module/radiotest/nullFeeType", method = RequestMethod.POST)
	public ModelAndView voidFeeType(@RequestParam("tid") RadioFeeType type, @RequestParam("action") String action){
		RadioTransactionService ts = Context.getService(RadioTransactionService.class);
		if (action.equalsIgnoreCase("void")){
			type.setVoided(!type.getVoided());
			ts.saveFeeType(type);
		} else if (action.equalsIgnoreCase("delete")){
			ts.deleteFeeType(type);
		}
		
		return new ModelAndView(REDIRECT_FORM);
	}
	
	@RequestMapping(value = "/module/radiotest/editFeeType", method = RequestMethod.POST)
	public ModelAndView editFeeType(@RequestParam("tid") RadioFeeType type, ModelMap model){
		model.addAttribute("feeType", type);
		
		return new ModelAndView(FEE_TYPE_FORM, model);
	}
}
