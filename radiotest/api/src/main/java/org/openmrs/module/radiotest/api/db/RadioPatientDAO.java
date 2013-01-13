package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;

public interface RadioPatientDAO {

	public RadioPatient savePatient(RadioPatient patient) throws DAOException;
	public RadioPatient getPatient(Integer patientId) throws DAOException;
	public List<RadioPatient> getAllPatients(boolean includeVoided) throws DAOException;
	public List<RadioPatient> search(String text) throws DAOException;
	
	public RadioCategory getCategory(Integer categoryId) throws DAOException;
	public List<RadioCategory> getAllCategories() throws DAOException;
	
	public RadioAlias getAlias(Integer aliasId) throws DAOException;
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided) throws DAOException;
}
