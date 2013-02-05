package org.openmrs.module.radiotest;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;

public class RadioAlias extends BaseOpenmrsData {
	
	private Integer id;
	
	private RadioPatient patient;
	private RadioCategory category;
	
	private String alias;
	private Date startDate;
	private Date endDate;
	
	private Boolean voided = Boolean.FALSE;
	
	public RadioAlias(){
		
	}
	
	public RadioAlias(String alias){
		setAlias(alias);
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public RadioPatient getPatient() {
		return patient;
	}

	public void setPatient(RadioPatient patient) {
		this.patient = patient;
	}

	public RadioCategory getCategory() {
		return category;
	}

	public void setCategory(RadioCategory category) {
		this.category = category;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
	
	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public Boolean isVoided() {
		return voided;
	}

	@Override
	public boolean equals(Object obj){
		if(!this.getClass().equals(obj))
			return false;
		
		RadioAlias other = (RadioAlias) obj;
		return this.getAlias().equals(other.getAlias());
	}
}
