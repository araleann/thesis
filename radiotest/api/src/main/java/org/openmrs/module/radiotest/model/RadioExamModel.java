package org.openmrs.module.radiotest.model;

import java.util.LinkedHashSet;

import org.openmrs.module.radiotest.RadioCategoryExam;
import org.openmrs.module.radiotest.RadioExam;
import org.springframework.util.AutoPopulatingList;

public class RadioExamModel {

	private RadioExam exam;
	private AutoPopulatingList<RadioCategoryExam> categoryFees;
	
	public RadioExam getExam() {
		return exam;
	}
	public void setExam(RadioExam exam) {
		this.exam = exam;
	}
	public AutoPopulatingList<RadioCategoryExam> getCategoryFees() {
		return categoryFees;
	}
	public void setCategoryFees(AutoPopulatingList<RadioCategoryExam> categoryFees) {
		this.categoryFees = categoryFees;
	}
	
	public RadioExam getFullExam(){
		exam.setCategoryFees(new LinkedHashSet<RadioCategoryExam>(categoryFees));
		
		return exam;
	}
}
