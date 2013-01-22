package org.openmrs.module.radiotest.type;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioExamType extends BaseOpenmrsMetadata {
	
	private Integer id;
	
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
