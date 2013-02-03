package org.openmrs.module.radiotest.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RadioNoteTypeController {
	
	private final String NOTE_TYPE_FORM = "/module/radiotest/noteTypeForm";
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("noteType")
	public RadioNoteType getNoteType(){
		return new RadioNoteType();
	}
	
	@ModelAttribute("noteTypes")
	public List<RadioNoteType> getNoteTypes(){
		return Context.getService(RadioTransactionService.class).getAllNoteTypes();
	}
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.POST)
	public ModelAndView saveNoteType(@ModelAttribute("noteType") RadioNoteType noteType, ModelMap model){
		Context.getService(RadioTransactionService.class).saveNoteType(noteType);
		
		return new ModelAndView("redirect:" + NOTE_TYPE_FORM + ".htm");
	}
}
