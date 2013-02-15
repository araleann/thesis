package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioFeeType;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;

public interface RadioTransactionDAO {

	public RadioTransaction saveTransaction(RadioTransaction trans) throws DAOException;
	public RadioTransaction getTransaction(Integer transId) throws DAOException;
	public List<RadioTransaction> getTransactions(RadioPatient patient) throws DAOException;
	
	public RadioNoteType saveNoteType(RadioNoteType noteType) throws DAOException;
	public RadioNoteType getNoteType(Integer typeId) throws DAOException;
	public List<RadioNoteType> getAllNoteTypes(boolean includeVoided) throws DAOException;
	public void deleteNoteType(RadioNoteType type) throws DAOException;
	
	public RadioTransExam saveTransExam(RadioTransExam exam) throws DAOException;
	public RadioTransExam getTransExam(Integer id) throws DAOException;
	
	public List<RadioTransaction> getPending(String prop) throws DAOException;
	
	public RadioFeeType saveFeeType(RadioFeeType feeType) throws DAOException;
	public RadioFeeType getFeeType(Integer typeId) throws DAOException;
	public List<RadioFeeType> getAllFeeTypes(boolean includeVoided) throws DAOException;
	public void deleteFeeType(RadioFeeType type) throws DAOException;
	
}
