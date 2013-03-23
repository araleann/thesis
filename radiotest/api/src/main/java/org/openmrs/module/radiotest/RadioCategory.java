package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioCategory extends BaseOpenmrsMetadata {

	private Integer id;
	private String category;
	
	private Boolean voided;
	
	public RadioCategory(){
		voided = false;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
}
