package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioResult extends BaseOpenmrsData {
	
	private Integer id;
	
	private Boolean positive;
	private String findings;
	private Boolean draft;
	
	private Boolean voided = false;
	
	public RadioResult(){
		
	}
	
	public RadioResult(boolean isInitialized){
		if(isInitialized){
			positive = false;
			draft = true;
		}
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

	public Boolean getPositive() {
		return positive;
	}

	public void setPositive(Boolean positive) {
		this.positive = positive;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public Boolean isVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

}
