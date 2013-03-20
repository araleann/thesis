package org.openmrs.module.radiotest;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

public class RadioReport {

	private DetachedCriteria criteria;
	private ProjectionList projectionList;
	private List<String> aliasList;
	
	
	private HashMap<String, String> headers;
			
	private Date startDate;
	private Date endDate;
	
	private RadioPatient patient;
	private RadioAlias alias;
	private RadioCategory category;
	
	private RadioTransaction transaction;
	
	public RadioReport(){
		headers = new HashMap<String, String>();
		headers.put("p.firstName", "First Name");
		headers.put("p.middleInitial", "Middle Initial");
		headers.put("p.lastName", "Last Name");
		headers.put("p.streetAddress", "Street Address");
		headers.put("p.barangay", "Barangay");
		headers.put("p.city", "City");
		headers.put("p.region", "Region");
		headers.put("p.caseNumber", "Case Number");
		headers.put("p.gender", "Gender");
		headers.put("p.civilStatus", "Civil Status");
		headers.put("p.institution", "Institution");
		headers.put("p.philhealth", "Philhealth");
		headers.put("a.alias", "Alias");
		headers.put("c.category", "Category");
	
		headers.put("t.visitDate", "Visit Date");
		headers.put("t.visitTime", "Visit Time");
		headers.put("t.paid", "Paid");
		headers.put("t.orNumber", "OR Number");
	}

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

	public List<String> getAliasList() {
		return aliasList;
	}

	public void setAliasList(List<String> aliasList) {
		this.aliasList = aliasList;
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

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	// CUSTOM FUNCTIONS	
	private Example getExample(String field){
		Object e = null;
		try {
			Field f = RadioReport.class.getDeclaredField(field);
			e = f.get(this);
			
			if(e == null){
				e = f.getType().newInstance();
			}
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		return Example.create(e).ignoreCase().excludeZeroes();
	}
	
	public RadioReport setProjectionList(List<String> list){
		aliasList = list;
		if(projectionList == null){
			projectionList = Projections.projectionList();
		}
		
		for(String prop : list){
			projectionList.add(Projections.property(prop));
		}
		
		return this;
	}
	
	public RadioReport generate(){
		criteria = DetachedCriteria
						.forClass(RadioTransExam.class, "te")
							.createCriteria("te.patient", "p")
								.add(getExample("patient"))
								.createCriteria("p.aliases", "a")
									.add(getExample("alias"))
									.createCriteria("a.category", "c")
										.add(getExample("category"))
							.createCriteria("te.transaction", "t")
								.add(getExample("transaction").enableLike());
										
		
		criteria.setProjection(projectionList);
		return this;
	}
	
	public String getHeaders(){
		StringBuilder sb = new StringBuilder();
		for(String alias : aliasList){
			sb.append(headers.get(alias) + ",");
		}
		sb.append("\n");
		
		return sb.toString();
	}

}
