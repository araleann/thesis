package org.openmrs.module.radiotest.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioReport;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.api.RadioReportService;
import org.openmrs.module.radiotest.model.RadioReportModel;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
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
//		binder.registerCustomEditor(String.class, new RadioStringPropertyEditor());
		binder.registerCustomEditor(RadioCategory.class, new RadioCategoryPropertyEditor());
	}
	
	@ModelAttribute("reportModel")
	public RadioReportModel getModel(){
		return new RadioReportModel();
	}
	
	@RequestMapping(value = REPORT_PAGE, method = RequestMethod.GET)
	public void showPage(ModelMap model){
		model.addAttribute("categories", Context.getService(RadioPatientService.class).getAllCategories());
	}
	
	@RequestMapping(value = REPORT_PAGE, method = RequestMethod.POST)
	public void getReport(@ModelAttribute("reportModel") RadioReportModel rm){
		RadioReport report = rm.getReport()
								.setProjectionList(rm.getProjectionList())
								.generate();
		Context.getService(RadioReportService.class).generateReport(report);
	}
}
