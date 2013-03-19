package org.openmrs.module.radiotest.model;

import java.util.List;

import org.openmrs.module.radiotest.RadioReport;

public class RadioReportModel {

	private RadioReport report;
	private List<String> projectionList;
	
	public RadioReportModel(){
		report = new RadioReport();
	}
	
	public RadioReport getReport() {
		return report;
	}
	public void setReport(RadioReport report) {
		this.report = report;
	}
	public List<String> getProjectionList() {
		return projectionList;
	}
	public void setProjectionList(List<String> projectionList) {
		this.projectionList = projectionList;
	}
	
}
