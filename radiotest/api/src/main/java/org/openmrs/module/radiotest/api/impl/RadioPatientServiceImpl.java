package org.openmrs.module.radiotest.api.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.api.db.RadioPatientDAO;
import org.springframework.transaction.annotation.Transactional;

public class RadioPatientServiceImpl extends BaseOpenmrsService implements RadioPatientService {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioPatientDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RadioPatientDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RadioPatientDAO getDao() {
	    return dao;
    }
    
	@Override
	public RadioPatient savePatient(RadioPatient patient) throws APIException {
		// TODO Auto-generated method stub
		return dao.savePatient(patient);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioPatient getPatient(Integer patientId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getPatient(patientId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioPatient> getAllPatients(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllPatients(includeVoided);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioCategory getCategory(Integer categoryId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getCategory(categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioCategory> getAllCategories() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllCategories();
	}

	@Override
	@Transactional(readOnly = true)
	public RadioAlias getAlias(Integer aliasId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getAlias(aliasId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAliases(patientId, includeVoided);
	}

}
