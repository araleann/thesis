package org.openmrs.module.radiotest;

import org.openmrs.BaseOpenmrsData;

public class RadioExam extends BaseOpenmrsData {
	
	private Integer id;
	
	private RadioExamType type;
	private String name;
	
	private Double studentFee;
	private Double employeeFee;
	private Double dependentFee;
	private Double outsiderFee;
	private Double seniorFee;
	
	private Double readingFee;
	private Double senReadingFee;
	
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

	public RadioExamType getType() {
		return type;
	}

	public void setType(RadioExamType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStudentFee() {
		return studentFee;
	}

	public void setStudentFee(Double studentFee) {
		this.studentFee = studentFee;
	}

	public Double getEmployeeFee() {
		return employeeFee;
	}

	public void setEmployeeFee(Double employeeFee) {
		this.employeeFee = employeeFee;
	}

	public Double getDependentFee() {
		return dependentFee;
	}

	public void setDependentFee(Double dependentFee) {
		this.dependentFee = dependentFee;
	}

	public Double getOutsiderFee() {
		return outsiderFee;
	}

	public void setOutsiderFee(Double outsiderFee) {
		this.outsiderFee = outsiderFee;
	}

	public Double getSeniorFee() {
		return seniorFee;
	}

	public void setSeniorFee(Double seniorFee) {
		this.seniorFee = seniorFee;
	}

	public Double getReadingFee() {
		return readingFee;
	}

	public void setReadingFee(Double readingFee) {
		this.readingFee = readingFee;
	}

	public Double getSenReadingFee() {
		return senReadingFee;
	}

	public void setSenReadingFee(Double senReadingFee) {
		this.senReadingFee = senReadingFee;
	}

}
