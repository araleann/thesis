package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;

public class RadioReport {

	private DetachedCriteria criteria;
	private ProjectionList projectionList;
	private List<String> aliasList;
	
	private HashMap<String, Projection> specialProj;
			
	private Date startDate;
	private Date endDate;
	
	private RadioPatient patient;
	private RadioAlias alias;
	private RadioCategory category;
	
	public RadioReport(){
		specialProj = new HashMap<String, Projection>();
		
		specialProj.put("t.positive", booleanProjection("t.positive", "P", "N", "Result"));
		specialProj.put("t.pending", booleanProjection("t.pending", "Y", "N", "Pending"));
		specialProj.put("t.paid", booleanProjection("t.paid", "Y", "N", "Paid"));
		
		specialProj.put("r.findings", Projections.sqlProjection("IF(r.positive, r.findings, null) as Result", new String[]{ "Result" }, new Type[]{ Hibernate.STRING }));
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
	
	// CUSTOM FUNCTIONS
	private Projection booleanProjection(String alias, String t, String f, String columnName){
		String sql = String.format("IF(%s, '%s', '%s') as '%s'",  alias, t, f, columnName);
		
		return Projections.sqlProjection(sql, new String[] { columnName }, new Type[] { Hibernate.STRING });
	}
	
	private Example wrapExample(Example e){
		return e.ignoreCase().excludeZeroes();
	}
	
	public RadioReport setProjectionList(List<String> list){
		aliasList = list;
		if(projectionList == null){
			projectionList = Projections.projectionList();
		}
		
		for(String prop : list){
			if(specialProj.containsKey(prop)){
				projectionList.add(specialProj.get(prop), prop);
			} else {
				projectionList.add(Projections.property(prop), prop);
			}
		}
		
		return this;
	}
	
	public RadioReport generate(){
		criteria = DetachedCriteria
						.forClass(RadioPatient.class, "p")
							.add(wrapExample(Example.create(patient)))
							.createCriteria("p.aliases", "a")
								.add(wrapExample(Example.create(alias)))
								.createCriteria("a.category", "c")
									.add(wrapExample(Example.create(category)));
		
		criteria.setProjection(projectionList);
		return this;
	}

}
