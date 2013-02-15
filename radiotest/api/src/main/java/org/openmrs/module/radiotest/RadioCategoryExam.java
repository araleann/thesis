package org.openmrs.module.radiotest;

import java.util.Set;

import org.openmrs.BaseOpenmrsData;

public class RadioCategoryExam extends BaseOpenmrsData {
	
	private Integer id;
	private RadioExam exam;
	private RadioCategory category;
	
	private Set<RadioFee> fees;

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

	public RadioExam getExam() {
		return exam;
	}

	public void setExam(RadioExam exam) {
		this.exam = exam;
	}

	public RadioCategory getCategory() {
		return category;
	}

	public void setCategory(RadioCategory category) {
		this.category = category;
	}

	public Set<RadioFee> getFees() {
		return fees;
	}

	public void setFees(Set<RadioFee> fees) {
		this.fees = fees;
	}

}
