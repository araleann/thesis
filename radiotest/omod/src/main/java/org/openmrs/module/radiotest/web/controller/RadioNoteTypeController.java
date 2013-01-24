package org.openmrs.module.radiotest.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RadioNoteTypeController {
	
	private final String NOTE_TYPE_FORM = "/module/radiotest/noteTypeForm";
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.GET)
	public void showForm(){
		
	}
	
	@ModelAttribute("noteType")
	public RadioNoteType getNoteType(){
		System.out.println("flag: modelAttribute");
		return new RadioNoteType();
	}
	
	@RequestMapping(value = NOTE_TYPE_FORM, method = RequestMethod.POST)
	public void saveNoteType(@ModelAttribute("noteType") RadioNoteType noteType, ModelMap model){
		// clears input field
		model.addAttribute("noteType", new RadioNoteType());
		System.out.println(noteType.getName());
//		Context.getService(RadioTransactionService.class).saveNoteType(noteType);
	}
}
