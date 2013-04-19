package org.openmrs.module.radiotest;

import java.util.Set;

import org.openmrs.BaseOpenmrsData;

public class RadioExam extends BaseOpenmrsData {
	
	private Integer id;
	
	private RadioExamType type;
	private String name;
	private String resultTemplate;
	
	private Set<RadioCategoryExam> categoryFees;
	
	private Boolean voided = Boolean.FALSE;
	
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

	public RadioExamType getType() {
		return type;
	}

	public void setType(RadioExamType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResultTemplate() {
		return resultTemplate;
	}

	public void setResultTemplate(String resultTemplate) {
		this.resultTemplate = resultTemplate;
	}

	public Set<RadioCategoryExam> getCategoryFees() {
		return categoryFees;
	}

	public void setCategoryFees(Set<RadioCategoryExam> categoryFees) {
		this.categoryFees = categoryFees;
	}

	public RadioCategoryExam getFees(RadioCategory category){
		for(RadioCategoryExam fee : categoryFees){
			if(category.equals(fee.getCategory())){
				return fee;
			}
		}
		return null;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
}
