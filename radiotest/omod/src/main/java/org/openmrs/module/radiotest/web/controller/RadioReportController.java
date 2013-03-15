package org.openmrs.module.radiotest.web.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.model.RadioReportModel;
import org.openmrs.module.radiotest.propertyeditor.RadioCategoryPropertyEditor;
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
		JFileChooser fc = new JFileChooser();
		try {
			if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				PrintWriter out = new PrintWriter(fc.getSelectedFile());
				List<String> list = rm.getProjectionList();
				for(String s : list){
					out.println(s);
				}
				out.println();
				
				out.println(rm.getReport().getPatient());
				out.println(rm.getReport().getAlias());
				out.println(rm.getReport().getCategory());
				out.close();
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
