package org.openmrs.module.radiotest.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioExamItem;
import org.openmrs.module.radiotest.RadioFeeType;
import org.openmrs.module.radiotest.RadioNote;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioResult;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.api.db.RadioTransactionDAO;
import org.openmrs.module.radiotest.propertyeditor.RadioComparator;
import org.springframework.transaction.annotation.Transactional;

public class RadioTransactionServiceImpl extends BaseOpenmrsService implements RadioTransactionService {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioTransactionDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RadioTransactionDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RadioTransactionDAO getDao() {
	    return dao;
    }
    
	@Override
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveTransaction(trans);
	}

	@Override
	public RadioTransaction getTransaction(Integer transId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getTransaction(transId);
	}

	@Override
	public RadioNoteType saveNoteType(RadioNoteType noteType)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveNoteType(noteType);
	}

	@Override
	public RadioNoteType getNoteType(Integer typeId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getNoteType(typeId);
	}

	@Override
	public List<RadioNoteType> getAllNoteTypes() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllNoteTypes(false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioTransaction> getTransactions(RadioPatient patient)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getTransactions(patient);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioTransExam getTransExam(Integer id) throws APIException {
		// TODO Auto-generated method stub
		return dao.getTransExam(id);
	}

	@Override
	public RadioTransExam saveTransExam(RadioTransExam exam)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveTransExam(exam);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioTransaction> getPending(String prop) throws APIException {
		// TODO Auto-generated method stub
		return dao.getPending(prop);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioTransaction updateTransaction(RadioTransaction trans)
			throws APIException {
		// TODO Auto-generated method stub
		trans = dao.getTransaction(trans.getId());
		trans.setExams(new LinkedHashSet<RadioTransExam>(trans.getExams()));
		
		List<RadioNote> notes = new ArrayList<RadioNote>(trans.getNotes());
		Collections.sort(notes, Collections.reverseOrder(new RadioComparator()));
		trans.setNotes(new LinkedHashSet<RadioNote>(notes));
		
		return trans;
	}

	@Override
	@Transactional(readOnly = true)
	public RadioTransExam updateTransExam(RadioTransExam exam)
			throws APIException {
		// TODO Auto-generated method stub
		exam = dao.getTransExam(exam.getId());
		exam.setFindings(new LinkedHashSet<RadioResult>(exam.getFindings()));
		exam.setItems(new LinkedHashSet<RadioExamItem>(exam.getItems()));
		return exam;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioNoteType> getAllNoteTypes(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllNoteTypes(includeVoided);
	}

	@Override
	public void deleteNoteType(RadioNoteType type) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteNoteType(type);
	}

	@Override
	public RadioFeeType saveFeeType(RadioFeeType type) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveFeeType(type);
	}

	@Override
	public RadioFeeType getFeeType(Integer typeId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getFeeType(typeId);
	}

	@Override
	public List<RadioFeeType> getAllFeeTypes() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllFeeTypes(false);
	}

	@Override
	public List<RadioFeeType> getAllFeeTypes(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllFeeTypes(includeVoided);
	}

	@Override
	public void deleteFeeType(RadioFeeType type) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteFeeType(type);
	}

	@Override
	public RadioResult saveResult(RadioResult result) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveResult(result);
	}
}
