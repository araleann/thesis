package org.openmrs.module.radiotest;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;

public class RadioNote extends BaseOpenmrsData {

	private Integer id;
	private String note;
	private Date date;
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
