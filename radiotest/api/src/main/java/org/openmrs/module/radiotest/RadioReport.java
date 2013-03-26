package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.List;

public class RadioReport {
	
	private List<String> fieldList;
			
	private Date startDate;
	private Date endDate;
	
	private RadioPatient patient;
	private RadioAlias alias;
	private RadioCategory category;
	
	private RadioExamType examType;
	private RadioExam exam;
	private RadioResult result;
	
	private RadioTransaction transaction;
	private RadioNoteType noteType;
	private RadioNote note;
	
	public RadioReport(){
		
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RadioPatient getPatient() {
		return patient;
	}

	public void setPatient(RadioPatient patient) {
		this.patient = patient;
	}

	public RadioAlias getAlias() {
		return alias;
	}

	public void setAlias(RadioAlias alias) {
		this.alias = alias;
	}
	
	public RadioCategory getCategory() {
		return category;
	}

	public void setCategory(RadioCategory category) {
		this.category = category;
	}

	public RadioExamType getExamType() {
		return examType;
	}

	public void setExamType(RadioExamType examType) {
		this.examType = examType;
	}

	public RadioExam getExam() {
		return exam;
	}

	public void setExam(RadioExam exam) {
		this.exam = exam;
	}

	public RadioResult getResult() {
		return result;
	}

	public void setResult(RadioResult result) {
		this.result = result;
	}

	public RadioTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(RadioTransaction transaction) {
		this.transaction = transaction;
	}

	public RadioNoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(RadioNoteType noteType) {
		this.noteType = noteType;
	}

	public RadioNote getNote() {
		return note;
	}

	public void setNote(RadioNote note) {
		this.note = note;
	}
}
