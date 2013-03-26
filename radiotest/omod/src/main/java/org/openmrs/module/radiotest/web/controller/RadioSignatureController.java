package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioSignature;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioSignaturePropertyEditor;
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
public class RadioSignatureController {

	private final String SIG_FORM = "/module/radiotest/signForm";
	private final String REDIRECT = "redirect:" + SIG_FORM + ".htm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioSignature.class, new RadioSignaturePropertyEditor());
	}
	
	@ModelAttribute("signature")
	public RadioSignature getSignature(){
		return new RadioSignature();
	}
	
	@ModelAttribute("signatures")
	public List<RadioSignature> getAllSignatures(ModelMap model){
		return Context.getService(RadioTransactionService.class).getAllSignatures(true);
	}
	
	@RequestMapping(value = SIG_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@RequestMapping(value = SIG_FORM, method = RequestMethod.POST)
	public ModelAndView saveSignature(@ModelAttribute("signature") RadioSignature sign){
		Context.getService(RadioTransactionService.class).saveSignature(sign);
		
		return new ModelAndView(REDIRECT);
	}
	
	@RequestMapping(value = "/module/radiotest/nullSignature", method = RequestMethod.POST)
	public ModelAndView nullSignature(@RequestParam("sid") RadioSignature sign, @RequestParam("action") String action){
		RadioTransactionService ts = Context.getService(RadioTransactionService.class);
		if (action.equalsIgnoreCase("void")){
			sign.setVoided(!sign.getVoided());
			ts.saveSignature(sign);
		} else if (action.equalsIgnoreCase("delete")){
			ts.deleteSignature(sign);
		}
		
		return new ModelAndView(REDIRECT);
	}
	
	@RequestMapping(value = "/module/radiotest/editSignature", method = RequestMethod.POST)
	public ModelAndView editSignature(@RequestParam("sid") RadioSignature sign, ModelMap model){
		model.addAttribute("signature", sign);
		
		return new ModelAndView(SIG_FORM, model);
	}
}
