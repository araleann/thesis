package org.openmrs.module.radiotest.api.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioCounter;
import org.openmrs.module.radiotest.RadioIndex;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
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
		patient = dao.savePatient(patient);
		
		patient.processIndex();
		System.out.println(patient.getIndex().getId());
		System.out.println(patient.getIndex().getAlias());
		dao.saveIndex(patient.getIndex());
		
		return patient;
	}

	@Override
	@Transactional(readOnly = true)
	public RadioPatient getPatient(Integer patientId) throws APIException {
		// TODO Auto-generated method stub
		RadioPatient patient = dao.getPatient(patientId);
		RadioIndex index = dao.getIndex(patient.getId());
		
		patient.setIndex(index);
		return patient;
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
		return dao.getAllCategories(false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RadioCategory> getAllCategories(boolean includeVoided) throws APIException {
		return dao.getAllCategories(includeVoided);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioAlias getAlias(Integer aliasId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getAlias(aliasId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioPatient> search(String text) throws APIException {
		// TODO Auto-generated method stub
		List<RadioIndex> indexes = dao.search(text);
		List<RadioPatient> patients = new ArrayList<RadioPatient>();
		
		for(RadioIndex i : indexes){
			patients.add(dao.getPatient(new Integer(i.getPatientId())));
		}
		
		return patients;
	}

	@Override
	public RadioCategory saveCategory(RadioCategory category)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveCategory(category);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioPatient updatePatient(RadioPatient patient) throws APIException {
		// TODO Auto-generated method stub
		RadioPatient p = this.getPatient(patient.getId());
		Set<RadioAlias> aliases = new LinkedHashSet<RadioAlias>(p.getAliases());
		Set<RadioTransaction> transactions = new LinkedHashSet<RadioTransaction>(p.getTransactions());
		
		patient.setIndex(p.getIndex());
		patient.setAliases(aliases);
		patient.setTransactions(transactions);
		return patient;
	}

	@Override
	@Transactional(readOnly = true)
	public RadioCounter getCounter() throws APIException {
		// TODO Auto-generated method stub
		return dao.getCounter();
	}

	@Override
	public RadioCounter saveCounter(RadioCounter counter) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveCounter(counter);
	}

	@Override
	public void deleteCategory(RadioCategory category) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteCategory(category);
	}

	@Override
	public void test(Integer pid) throws APIException {
		// TODO Auto-generated method stub
		dao.test(pid);
	}

}
