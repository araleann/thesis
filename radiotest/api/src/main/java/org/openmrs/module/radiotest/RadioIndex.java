package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsMetadata;

public class RadioIndex extends BaseOpenmrsMetadata {

	private Integer id;
	
	private String name;
	private String alias;
		
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
