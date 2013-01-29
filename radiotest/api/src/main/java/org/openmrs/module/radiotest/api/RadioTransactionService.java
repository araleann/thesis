package org.openmrs.module.radiotest.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioTransactionService extends OpenmrsService {
	
	public RadioTransaction saveTransaction(RadioTransaction trans) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioTransaction getTransaction(Integer transId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioTransaction> getTransactions(RadioPatient patient) throws APIException;
	
	public RadioNoteType saveNoteType(RadioNoteType noteType) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioNoteType getNoteType(Integer typeId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioNoteType> getAllNoteTypes() throws APIException;
	
	public RadioTransExam saveTransExam(RadioTransExam exam) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioTransExam getTransExam(Integer id) throws APIException;
}
