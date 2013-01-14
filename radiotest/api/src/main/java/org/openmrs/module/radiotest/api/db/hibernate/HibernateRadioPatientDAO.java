package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.api.db.RadioPatientDAO;

public class HibernateRadioPatientDAO implements RadioPatientDAO {
	
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
    
    private String addWildcards(String text){
    	return "%" + text + "%";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioPatient> getAllPatients(boolean includeVoided)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioPatient.class);
		if(!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		return (List<RadioPatient>) criteria.list();
	}

	@Override
	public RadioCategory getCategory(Integer categoryId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioCategory) sessionFactory.getCurrentSession().get(RadioCategory.class, categoryId);

	}

	@SuppressWarnings("unchecked")
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
		criteria.add(Restrictions.eq("patient_id", patientId));
		
		if (!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioPatient> search(String text) throws DAOException {
		// TODO Auto-generated method stub
		String searchString = addWildcards(text);
		List<RadioPatient> list = sessionFactory.getCurrentSession()
										.createCriteria(RadioPatient.class)
										.createAlias("aliases", "a")
										.add(Restrictions.disjunction()
												.add(Restrictions.like("firstName", searchString))
												.add(Restrictions.like("lastName", searchString))
												.add(Restrictions.like("a.alias", searchString)))
										.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
										.list();
		
		return (List<RadioPatient>) list;
	}
}
