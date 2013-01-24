package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioCategoryExam extends BaseOpenmrsData {
	
	private Integer id;
	private RadioExam exam;
	private RadioCategory category;
	
	private Double examFee;
	private Double readingFee;

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

	public Double getExamFee() {
		return examFee;
	}

	public void setExamFee(Double examFee) {
		this.examFee = examFee;
	}

	public Double getReadingFee() {
		return readingFee;
	}

	public void setReadingFee(Double readingFee) {
		this.readingFee = readingFee;
	}

}
