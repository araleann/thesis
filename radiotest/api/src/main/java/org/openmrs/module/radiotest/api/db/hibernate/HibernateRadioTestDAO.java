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
package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.db.RadioTestDAO;

/**
 * It is a default implementation of  {@link RadioTestDAO}.
 */
@SuppressWarnings("unchecked")
public class HibernateRadioTestDAO implements RadioTestDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }

	@Override
	public RadioPatient savePatient(RadioPatient patient) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(patient);
		return patient;
	}

	@Override
	public RadioPatient getPatient(Integer patientId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioPatient) sessionFactory.getCurrentSession().get(RadioPatient.class, patientId);
	}

	@Override
	public List<RadioPatient> getAllPatients(boolean includeVoided)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioPatient.class);
		if(!includeVoided){
			criteria.add(Expression.eq("voided", false));
		}
		return (List<RadioPatient>) criteria.list();
	}

	@Override
	public RadioCategory getCategory(Integer categoryId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioCategory) sessionFactory.getCurrentSession().get(RadioCategory.class, categoryId);

	}

	@Override
	public List<RadioCategory> getAllCategories() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioCategory.class);
		return (List<RadioCategory>) criteria.list();
	}

	@Override
	public RadioAlias getAlias(Integer aliasId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioAlias) sessionFactory.getCurrentSession().get(RadioAlias.class, aliasId);
	}

	@Override
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioAlias.class);
		criteria.add(Expression.eq("patient_id", patientId));
		
		if (!includeVoided){
			criteria.add(Expression.eq("voided", false));
		}
		return null;
	}

	// EXAM FUNCTIONS
	@Override
	public RadioExam saveExam(RadioExam exam) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(exam);
		return exam;
	}
	
	@Override
	public List<RadioExam> getAllExams() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioExam.class);
		return (List<RadioExam>) criteria.list();
	}

	@Override
	public RadioExam getExam(Integer examId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioExam) sessionFactory.getCurrentSession().get(RadioExam.class, examId);
	}
	
	@Override
	public RadioExamType saveExamType(RadioExamType type) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(type);
		return type;
	}
	
	@Override
	public List<RadioExamType> getAllExamTypes() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioExamType.class);
		return (List<RadioExamType>) criteria.list();
	}

	@Override
	public RadioExamType getExamType(Integer typeId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioExamType) sessionFactory.getCurrentSession().get(RadioExamType.class, typeId);
	}	

	// TRANSACTION FUNCTIONS
	@Override
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(trans);
		return trans;
	}

}