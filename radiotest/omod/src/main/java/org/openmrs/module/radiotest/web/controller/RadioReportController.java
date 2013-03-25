package org.openmrs.module.radiotest.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioReport;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.api.RadioReportService;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.propertyeditor.RadioBooleanPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioExamPropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioExamTypePropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioNoteTypePropertyEditor;
import org.openmrs.module.radiotest.propertyeditor.RadioStringPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RadioReportController {

	private final String REPORT_PAGE = "/module/radiotest/report";
	
	@InitBinder
	public void initBinder(WebRequest request, WebDataBinder binder){
		binder.registerCustomEditor(String.class, new RadioStringPropertyEditor());
		binder.registerCustomEditor(Boolean.class, new RadioBooleanPropertyEditor());
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
		binder.registerCustomEditor(RadioExamType.class, new RadioExamTypePropertyEditor());
		binder.registerCustomEditor(RadioExam.class, new RadioExamPropertyEditor());
		binder.registerCustomEditor(RadioNoteType.class, new RadioNoteTypePropertyEditor());
	}
	
	@ModelAttribute("report")
	public RadioReport getReport(){
		return new RadioReport();
	}
	
	@RequestMapping(value = REPORT_PAGE, method = RequestMethod.GET)
	public void showPage(ModelMap model){
		RadioExamService es = Context.getService(RadioExamService.class);
		model.addAttribute("categories", Context.getService(RadioPatientService.class).getAllCategories());
		model.addAttribute("examTypes", es.getAllExamTypes());
		model.addAttribute("exams", es.getAllExams());
		model.addAttribute("noteTypes", Context.getService(RadioTransactionService.class).getAllNoteTypes());
	}
	
	@RequestMapping(value = REPORT_PAGE, method = RequestMethod.POST)
	public void getReport(@ModelAttribute("report") RadioReport report){
		Context.getService(RadioReportService.class).generateReport(report);
	}
}
