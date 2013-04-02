package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioCounter;
import org.openmrs.module.radiotest.RadioIndex;
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
    
    private String[] format(String text){
    	String[] searchText = text.split(" ");
    	int size = searchText.length;
    	
    	String[] search = new String[size];
    	for(int i=0; i<size; i++){
    		search[i] = "%" + searchText[i] + "%";
    	}
    	
    	return search;
    }
    
	@Override
	public RadioPatient savePatient(RadioPatient patient) throws DAOException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		patient = (RadioPatient) session.merge(patient);
		session.saveOrUpdate(patient);
		
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
	public List<RadioCategory> getAllCategories(boolean includeVoided) throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioCategory.class);
		
		if (!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		
		return (List<RadioCategory>) criteria.list();
	}

	@Override
	public RadioAlias getAlias(Integer aliasId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioAlias) sessionFactory.getCurrentSession().get(RadioAlias.class, aliasId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioIndex> search(String text) throws DAOException {
		// TODO Auto-generated method stub
		String[] searchString = format(text);
		Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(RadioIndex.class);
		
		Conjunction conj = Restrictions.conjunction();
		for(String t : searchString){
			conj.add(Restrictions.disjunction()
					.add(Restrictions.like("name", t))
					.add(Restrictions.like("alias", t)));
		}
		
		criteria.add(conj);
		
		return criteria.list();
	}

	@Override
	public RadioCategory saveCategory(RadioCategory category)
			throws DAOException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		category = (RadioCategory) session.merge(category);
		session.saveOrUpdate(category);
		
		return category;
	}

	@Override
	public RadioCounter getCounter() throws DAOException {
		// TODO Auto-generated method stub
		return (RadioCounter) sessionFactory.getCurrentSession().get(RadioCounter.class, new Integer(1));
	}

	@Override
	public RadioCounter saveCounter(RadioCounter counter) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(counter);
		return counter;
	}

	@Override
	public void deleteCategory(RadioCategory category) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public RadioIndex saveIndex(RadioIndex index) throws DAOException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		index = (RadioIndex) session.merge(index);
		session.saveOrUpdate(index);
		
		return index;
	}

	@Override
	public RadioIndex getIndex(Integer patientId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioIndex) sessionFactory.getCurrentSession()
								.createCriteria(RadioIndex.class)
									.add(Restrictions.eq("patientId", patientId.toString()))
									.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void test(Integer pid) throws DAOException {
		// TODO Auto-generated method stub
		System.out.println("TEST START");
		Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(RadioIndex.class)
									.add(Restrictions.eq("patientId", pid.toString()));
		
		List<RadioIndex> list = criteria.list();
		for(RadioIndex i : list){
			System.out.println(i.getName());
		}
		System.out.println("TEST END");
	}
}
