package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioCounter;
import org.openmrs.module.radiotest.RadioIndex;
import org.openmrs.module.radiotest.RadioPatient;

public interface RadioPatientDAO {

	public RadioPatient savePatient(RadioPatient patient) throws DAOException;
	public RadioPatient getPatient(Integer patientId) throws DAOException;
	public List<RadioPatient> getAllPatients(boolean includeVoided) throws DAOException;
	public List<RadioIndex> search(String text) throws DAOException;
	
	public RadioCategory getCategory(Integer categoryId) throws DAOException;
	public List<RadioCategory> getAllCategories(boolean includeVoided) throws DAOException;
	public RadioCategory saveCategory(RadioCategory category) throws DAOException;
	public void deleteCategory(RadioCategory category) throws DAOException;
	
	public RadioAlias getAlias(Integer aliasId) throws DAOException;
	
	public RadioCounter getCounter() throws DAOException;
	public RadioCounter saveCounter(RadioCounter counter) throws DAOException;
	
	public RadioIndex saveIndex(RadioIndex index) throws DAOException;
	public RadioIndex getIndex(Integer patientId) throws DAOException;
	
	public void test(Integer pid) throws DAOException;
}
