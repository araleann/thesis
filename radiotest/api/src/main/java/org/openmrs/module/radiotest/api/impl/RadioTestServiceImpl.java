/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.radiotest.api.impl;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTestService;
import org.openmrs.module.radiotest.api.db.RadioTestDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * It is a default implementation of {@link RadioTestService}.
 */
public class RadioTestServiceImpl extends BaseOpenmrsService implements RadioTestService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioTestDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RadioTestDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RadioTestDAO getDao() {
	    return dao;
    }

    // PATIENT SERVICES
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

	// EXAM SERVICES
	@Override
	public RadioExam saveExam(RadioExam exam) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveExam(exam);
	}
	
	@Override
	public List<RadioExam> getAllExams() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExams();
	}

	@Override
	public RadioExam getExam(Integer examId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getExam(examId);
	}
	
	@Override
	public RadioExamType saveExamType(RadioExamType type) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveExamType(type);
	}
	
	@Override
	public List<RadioExamType> getAllExamTypes() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExamTypes();
	}

	@Override
	public RadioExamType getExamType(Integer typeId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getExamType(typeId);
	}

	// TRANSACTION SERVICES
	@Override
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveTransaction(trans);
	}
	
}