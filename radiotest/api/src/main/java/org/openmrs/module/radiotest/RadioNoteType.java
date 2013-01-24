package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioNoteType extends BaseOpenmrsMetadata {
	
	private Integer id;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
