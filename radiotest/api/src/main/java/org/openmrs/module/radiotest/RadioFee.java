package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioFee extends BaseOpenmrsData {
	
	private Integer id;
	private RadioFeeType type;
	private RadioCategoryExam category;
	
	private double amount;

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

	public RadioFeeType getType() {
		return type;
	}

	public void setType(RadioFeeType type) {
		this.type = type;
	}

	public RadioCategoryExam getCategory() {
		return category;
	}

	public void setCategory(RadioCategoryExam category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
