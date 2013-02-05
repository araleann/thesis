package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioNoteTypePropertyEditor;
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
public class RadioNoteTypeController {
	
	private final String NOTE_TYPE_FORM = "/module/radiotest/noteTypeForm";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(RadioNoteType.class, new RadioNoteTypePropertyEditor());
	}
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("noteType")
	public RadioNoteType getNoteType(){
		return new RadioNoteType();
	}
	
	@ModelAttribute("noteTypes")
	public List<RadioNoteType> getNoteTypes(){
		return Context.getService(RadioTransactionService.class).getAllNoteTypes(true);
	}
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.POST)
	public ModelAndView saveNoteType(@ModelAttribute("noteType") RadioNoteType noteType, ModelMap model){
		Context.getService(RadioTransactionService.class).saveNoteType(noteType);
		
		return new ModelAndView("redirect:" + NOTE_TYPE_FORM + ".htm");
	}
	
	@RequestMapping(value = "/module/radiotest/nullNoteType", method = RequestMethod.POST)
	public ModelAndView voidNoteType(@RequestParam("nid") RadioNoteType type, @RequestParam("action") String action){
		RadioTransactionService ts = Context.getService(RadioTransactionService.class);
		if (action.equalsIgnoreCase("void")){
			type.setVoided(!type.getVoided());
			ts.saveNoteType(type);
		} else if (action.equalsIgnoreCase("delete")){
			ts.deleteNoteType(type);
		}
		
		return new ModelAndView("redirect:" + NOTE_TYPE_FORM + ".htm");
	}
}
