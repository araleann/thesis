package org.openmrs.module.radiotest.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.radiotest.RadioNote;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.springframework.util.AutoPopulatingList;

public class RadioTransactionModel {
	
	private RadioTransaction transaction;
	private AutoPopulatingList<RadioTransExam> exams;
	
	private List<RadioNoteType> noteTypes;
	private RadioNote note;
	
	public RadioTransactionModel() {
		transaction = new RadioTransaction();
		
		exams = new AutoPopulatingList<RadioTransExam>(RadioTransExam.class);
		
		noteTypes = Context.getService(RadioTransactionService.class).getAllNoteTypes();
		note = new RadioNote();
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

	public List<RadioNoteType> getNoteTypes() {
		return noteTypes;
	}

	public void setNoteTypes(List<RadioNoteType> noteTypes) {
		this.noteTypes = noteTypes;
	}

	public RadioNote getNote() {
		return note;
	}

	public void setNote(RadioNote note) {
		this.note = note;
	}

	public RadioTransaction getFullTransaction(){
		if (transaction.getExams() == null){
			transaction.setExams(new LinkedHashSet<RadioTransExam>(exams));
		}
		
		if (note.getId() != null){
			note.setDate(new Date());
			if (transaction.getNotes() == null){
				transaction.setNotes(new LinkedHashSet<RadioNote>());
			}
			transaction.getNotes().add(note);
		}
		return transaction;
	}
}
