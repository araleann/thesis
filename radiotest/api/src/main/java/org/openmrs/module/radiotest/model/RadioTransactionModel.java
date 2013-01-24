package org.openmrs.module.radiotest.model;

import java.util.LinkedHashSet;

import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.springframework.util.AutoPopulatingList;

public class RadioTransactionModel {
	
	private RadioTransaction transaction;
	private AutoPopulatingList<RadioTransExam> exams;
	
	public RadioTransactionModel() {
		transaction = new RadioTransaction();
		exams = new AutoPopulatingList<RadioTransExam>(RadioTransExam.class);
	}

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	public AutoPopulatingList<RadioTransExam> getExams() {
		return exams;
	}

	public void setExams(AutoPopulatingList<RadioTransExam> exams) {
		this.exams = exams;
	}

	public RadioTransaction getFullTransaction(){
		transaction.setExams(new LinkedHashSet<RadioTransExam>(exams));
		
		return transaction;
	}
}
