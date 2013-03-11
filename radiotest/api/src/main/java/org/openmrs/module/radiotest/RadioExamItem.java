package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioExamItem extends BaseOpenmrsData {

	private Integer id;
	
	private RadioTransExam exam;
	private RadioItem item;
	
	private int quantity;
	
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

	public RadioTransExam getExam() {
		return exam;
	}

	public void setExam(RadioTransExam exam) {
		this.exam = exam;
	}

	public RadioItem getItem() {
		return item;
	}

	public void setItem(RadioItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
