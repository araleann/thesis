package org.openmrs.module.radiotest;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;

public class RadioReport {

	private DetachedCriteria criteria;
	private ProjectionList projectionList;
	
	private Date startDate;
	private Date endDate;
	
	private RadioPatient patient;
	private RadioAlias alias;
	private RadioCategory category;

	public DetachedCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

	public ProjectionList getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(ProjectionList projectionList) {
		this.projectionList = projectionList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RadioPatient getPatient() {
		return patient;
	}

	public void setPatient(RadioPatient patient) {
		this.patient = patient;
	}

	public RadioAlias getAlias() {
		return alias;
	}

	public void setAlias(RadioAlias alias) {
		this.alias = alias;
	}

	public RadioCategory getCategory() {
		return category;
	}

	public void setCategory(RadioCategory category) {
		this.category = category;
	}
}
