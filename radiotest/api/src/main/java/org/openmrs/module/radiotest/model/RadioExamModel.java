package org.openmrs.module.radiotest.model;

import java.util.LinkedHashSet;

import org.openmrs.module.radiotest.RadioCategoryExam;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioFee;
import org.springframework.util.AutoPopulatingList;

public class RadioExamModel {

	private int numOfFeeTypes;
	private RadioExam exam;
	private AutoPopulatingList<RadioCategoryExam> categoryFees;
	private AutoPopulatingList<RadioFee> fees;
	
	public RadioExamModel(){
		exam = new RadioExam();
		categoryFees = new AutoPopulatingList<RadioCategoryExam>(RadioCategoryExam.class);
		fees = new AutoPopulatingList<RadioFee>(RadioFee.class);
	}
	
	public RadioExamModel(RadioExam e){
		this();
		exam = e;
		for(RadioCategoryExam catFee : e.getCategoryFees()){
			if (!catFee.getCategory().getVoided()){
				categoryFees.add(catFee);
				
				for(RadioFee fee : catFee.getFees()){
					if (!fee.getType().getVoided()){
						fees.add(fee);
					}
				}
			}
		}
	}
	
	public int getNumOfFeeTypes() {
		return numOfFeeTypes;
	}

	public void setNumOfFeeTypes(int numOfFeeTypes) {
		this.numOfFeeTypes = numOfFeeTypes;
	}

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
	
	public AutoPopulatingList<RadioFee> getFees() {
		return fees;
	}

	public void setFees(AutoPopulatingList<RadioFee> fees) {
		this.fees = fees;
	}

	public RadioExam getFullExam(){
		int i = 0;
		for(RadioCategoryExam catFee : categoryFees){
			catFee.setFees(new LinkedHashSet<RadioFee>(fees.subList(i, i+numOfFeeTypes)));
			i = i + numOfFeeTypes;
		}
		
		exam.setCategoryFees(new LinkedHashSet<RadioCategoryExam>(categoryFees));
		return exam;
	}
}
