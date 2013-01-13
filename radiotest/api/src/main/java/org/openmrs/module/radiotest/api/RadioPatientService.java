package org.openmrs.module.radiotest.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioPatientService extends OpenmrsService {
	
	public RadioPatient savePatient(RadioPatient patient) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioPatient getPatient(Integer patientId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioPatient> getAllPatients(boolean includeVoided) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioCategory getCategory(Integer categoryId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioCategory> getAllCategories() throws APIException;
	
	@Transactional(readOnly = true)
	public RadioAlias getAlias(Integer aliasId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioPatient> search(String text) throws APIException;

}
