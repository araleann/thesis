package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioNoteType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransExam;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.db.RadioTransactionDAO;

public class HibernateRadioTransactionDAO implements RadioTransactionDAO {

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
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(trans);
		return trans;
	}

	@Override
	public RadioTransaction getTransaction(Integer transId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioTransaction) sessionFactory.getCurrentSession().get(RadioTransaction.class, transId);
	}

	@Override
	public RadioNoteType saveNoteType(RadioNoteType noteType)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(noteType);
		return noteType;
	}

	@Override
	public RadioNoteType getNoteType(Integer typeId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioNoteType) sessionFactory.getCurrentSession().get(RadioNoteType.class, typeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioNoteType> getAllNoteTypes(boolean includeVoided) throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioNoteType.class);
		
		if (!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		
		return (List<RadioNoteType>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioTransaction> getTransactions(RadioPatient patient)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(RadioTransaction.class)
								.add(Restrictions.eq("patient", patient));
		return (List<RadioTransaction>) criteria.list();
	}

	@Override
	public RadioTransExam getTransExam(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioTransExam) sessionFactory.getCurrentSession().get(RadioTransExam.class, id);
	}

	@Override
	public RadioTransExam saveTransExam(RadioTransExam exam)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(exam);
		return exam;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioTransaction> getPending(String prop) throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioTransaction.class);
		
		if(prop.equalsIgnoreCase("payment")){
			criteria.add(Restrictions.eq("paid", false));
		}
		
		if(prop.equalsIgnoreCase("results")){
			criteria.add(Restrictions.eq("pending", true))
						.add(Restrictions.eq("paid", true));
		}
		
		if(prop.equalsIgnoreCase("claim")){
			criteria.add(Restrictions.eq("claimed", false))
						.add(Restrictions.eq("pending", false));
		}
		
		return (List<RadioTransaction>) criteria.list();
	}

	@Override
	public void deleteNoteType(RadioNoteType type) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(type);
	}
}
