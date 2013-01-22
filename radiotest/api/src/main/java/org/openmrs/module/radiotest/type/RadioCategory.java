package org.openmrs.module.radiotest.type;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioCategory extends BaseOpenmrsMetadata {

	private Integer id;
	
	private String category;
	
	public RadioCategory(){
		
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
	
	@Override
	public boolean equals(Object o){
		RadioCategory other = (RadioCategory) o;
		return this.id == other.getId();
	}
}
