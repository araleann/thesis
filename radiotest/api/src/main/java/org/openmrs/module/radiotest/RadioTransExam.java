package org.openmrs.module.radiotest;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;

public class RadioTransExam extends BaseOpenmrsData {
	
	private Integer id;
	
	private RadioPatient patient;
	private RadioTransaction transaction;
	private RadioExam exam;
	
	private Date schedule;
	
	private boolean pending = true;
	private boolean borrowed = false;
		
	private Set<RadioResult> findings;
		
	private Boolean voided = Boolean.FALSE;
	
	// not saved in database
	private Double examFee;
	private Double readingFee;
	private RadioResult result;
	
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

	public Set<RadioResult> getFindings() {
		return findings;
	}

	public void setFindings(Set<RadioResult> findings) {
		this.findings = findings;
	}
	
	public void addFinding(RadioResult res){
		if (findings == null){
			findings = new LinkedHashSet<RadioResult>();
		}
		for(RadioResult r: findings){
			r.setVoided(Boolean.TRUE);
		}
		findings.add(res);
	}
	
	public boolean hasResult(){
		boolean r = findings != null && !findings.isEmpty();
		if (r){
			for(RadioResult res : findings){
				if (!res.isVoided().booleanValue()){
					if (res.isDraft()){
						findings.remove(res);
					}
					result = res;
				}
			}
		}
		return r;
	}
	
	public RadioResult getResult(){
		return result;
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
