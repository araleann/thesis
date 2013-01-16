package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;

public class RadioTransaction extends BaseOpenmrsData {

	private Integer id;
	
	private RadioPatient patient;
	private Set<RadioTransExam> exams;
	
	private Date visitDate;
	private Date visitTime;
	private boolean pending;
	private boolean paid;
	
	private Double readingFee;
	private Double examFee;
	private String purpose;
	private Set<RadioNote> notes;
	
	private boolean draft;
	private Boolean voided;
	
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

	public Set<RadioTransExam> getExams() {
		return exams;
	}

	public void setExams(Set<RadioTransExam> exams) {
		this.exams = exams;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Double getReadingFee() {
		return readingFee;
	}

	public void setReadingFee(Double readingFee) {
		this.readingFee = readingFee;
	}

	public Double getExamFee() {
		return examFee;
	}

	public void setExamFee(Double examFee) {
		this.examFee = examFee;
	}
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Set<RadioNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<RadioNote> notes) {
		this.notes = notes;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public Boolean isVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

}
