package org.openmrs.module.radiotest.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.type.RadioNoteType;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioTransactionService extends OpenmrsService {
	
	public RadioTransaction saveTransaction(RadioTransaction trans) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioTransaction getTransaction(Integer transId) throws APIException;
	
	public RadioNoteType saveNoteType(RadioNoteType noteType) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioNoteType getNoteType(Integer typeId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioNoteType> getAllNoteTypes() throws APIException;
}
