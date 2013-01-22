package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.type.RadioNoteType;

public interface RadioTransactionDAO {

	public RadioTransaction saveTransaction(RadioTransaction trans) throws DAOException;
	public RadioTransaction getTransaction(Integer transId) throws DAOException;
	
	public RadioNoteType saveNoteType(RadioNoteType noteType) throws DAOException;
	public RadioNoteType getNoteType(Integer typeId) throws DAOException;
	public List<RadioNoteType> getAllNoteTypes() throws DAOException;
}
