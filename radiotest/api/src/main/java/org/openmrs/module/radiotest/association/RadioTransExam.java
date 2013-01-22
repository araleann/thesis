package org.openmrs.module.radiotest.association;

import java.util.Date;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioResult;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.type.RadioCategory;

public class RadioTransExam extends BaseOpenmrsData {
	
	private Integer id;
	
	private RadioPatient patient;
	private RadioTransaction transaction;
	private RadioExam exam;
	
	private Date schedule;
	
	private boolean pending;
	private boolean borrowed;
	
	private Date examDate;
	private Date examTime;
	
	private Set<RadioResult> findings;
		
	private Boolean voided;
	
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

	public RadioPatient getPatient() {
		return patient;
	}

	public void setPatient(RadioPatient patient) {
		this.patient = patient;
	}

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	public RadioExam getExam() {
		return exam;
	}

	public void setExam(RadioExam exam) {
		this.exam = exam;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public Set<RadioResult> getFindings() {
		return findings;
	}

	public void setFindings(Set<RadioResult> findings) {
		this.findings = findings;
	}

	public Boolean isVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
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

	public RadioCategoryExam getFees(RadioCategory category){
		RadioCategoryExam fee = exam.getFees(category);
		this.examFee = fee.getExamFee();
		this.readingFee = fee.getReadingFee();
		
		return fee;
	}

}
