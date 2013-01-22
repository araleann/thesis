package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.association.RadioCategoryExam;
import org.openmrs.module.radiotest.association.RadioTransExam;
import org.openmrs.module.radiotest.type.RadioCategory;

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
	
	private Double total;
	
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
		computeFees();
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
	
	public int getNumberOfExams(){
		return exams.size();
	}
	
	public Double getTotal(){
		return total;
	}
	
	private void computeFees(){
		// for debugging purposes
		patient = Context.getService(RadioPatientService.class).getPatient(new Integer(1));
		
		Iterator<RadioTransExam> iter = exams.iterator();
		RadioCategory category = patient.getCategory();
		double examFee = 0, readingFee = 0;
		
		while(iter.hasNext()){
			RadioTransExam exam = iter.next();
			RadioCategoryExam fee = exam.getFees(category);
			examFee = examFee + checkDouble(fee.getExamFee());
			readingFee = readingFee + checkDouble(fee.getReadingFee());
		}
		
		this.examFee = new Double(examFee);
		this.readingFee = new Double(readingFee);
		this.total = new Double(examFee + readingFee);
	}
	
	private double checkDouble(Double d){
		return d == null? 0 : d.doubleValue();
	}
}
